package com.bookaroom.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = ConversationDTO.QUERY_NAME_FIND_BY_A_AND_B_ROLE,
        query = "SELECT"
                + "    c.id,"
                + "    CASE WHEN ua.id = ?1 THEN ub.name ELSE ua.name END as bName,"
                + "    CASE WHEN ua.id = ?1 THEN fub.server_path ELSE fua.server_path END as bPicturePath,"
                + "    m.message as lastMessage,"
                + "    CASE WHEN ua.id = m.sender THEN ua.name ELSE ub.name END as lastMessageUserName,"
                + "    m.date as lastMessageDate"
                + "  FROM CONVERSATIONS c"
                + "  JOIN USERS ua on ua.id = c.user_a"
                + "  LEFT JOIN FILE_UPLOADS fua on fua.id = ua.picture_file_upload_id"
                + "  JOIN USERS ub on ub.id = c.user_b"
                + "  LEFT JOIN FILE_UPLOADS fub on fub.id = ub.picture_file_upload_id"
                + "  LEFT JOIN MESSAGES m"
                + "    on m.conversation_id = c.id"
                + "    AND NOT EXISTS ( SELECT 1 FROM MESSAGES m2 WHERE m2.conversation_id = m.conversation_id AND m2.date > m.date AND m2.id > m.id )"
                + "  WHERE ("
                + "    (ua.id = ?1 AND ub.role = ?2)"
                + "    OR (ub.id = ?1 and ua.role = ?2)"
                + "  )"
                + "  ORDER BY c.last_access DESC")})
@Entity
@Table(name = "CONVERSATIONS")
public class ConversationDTO {
    
    public static final String QUERY_NAME_PREFIX = "ConversationDTO.";
    public static final String QUERY_NAME_FIND_BY_A_AND_B_ROLE = QUERY_NAME_PREFIX + "findByAndAndBRole";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_A")
    private Long userA;

    @Column(name = "USER_B")
    private Long userB;

    @Column(name = "LAST_ACCESS")
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserA()
    {
        return userA;
    }

    public void setUserA(Long userA)
    {
        this.userA = userA;
    }

    public Long getUserB()
    {
        return userB;
    }

    public void setUserB(Long userB)
    {
        this.userB = userB;
    }

    public Date getLastAccess()
    {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess)
    {
        this.lastAccess = lastAccess;
    }

    @Override
    public String toString()
    {
        return "ConversationDTO [id="
               + id
               + ", userA="
               + userA
               + ", userB="
               + userB
               + ", lastAccess="
               + lastAccess
               + "]";
    }

}
