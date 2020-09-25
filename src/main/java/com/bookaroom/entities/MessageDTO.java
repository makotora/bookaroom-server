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
        name = MessageDTO.QUERY_NAME_FIND_CONVERSATION_MESSAGES,
        query = "  SELECT"
                + "    fu.server_path as userPicturePath,"
                + "    u.name as userName,"
                + "    m.message as message,"
                + "    m.date as date"
                + "  FROM MESSAGES m"
                + "  JOIN USERS u on u.id = m.sender"
                + "  JOIN FILE_UPLOADS fu on fu.id = u.picture_file_upload_id"
                + "  WHERE m.conversation_id = ?1"
                + "  ORDER BY DATE DESC")})
@Entity
@Table(name = "MESSAGES")
public class MessageDTO {
    public static final String QUERY_NAME_PREFIX = "MessagesDTO.";
    public static final String QUERY_NAME_FIND_CONVERSATION_MESSAGES = "findConversationMessages";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONVERSATION_ID")
    private Long conversationId;
	
    @Column(name = "MESSAGE")
	private String message;
	
    @Column(name = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
    @Column(name = "SEEN")
    private Boolean seen;
	
    @Column(name = "SENDER")
    private Long sender;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getConversationId()
    {
        return conversationId;
    }

    public void setConversationId(Long conversationId)
    {
        this.conversationId = conversationId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Boolean getSeen()
    {
        return seen;
    }

    public void setSeen(Boolean seen)
    {
        this.seen = seen;
    }

    public Long getSender()
    {
        return sender;
    }

    public void setSender(Long sender)
    {
        this.sender = sender;
    }

    @Override
    public String toString()
    {
        return "MessageDTO [id="
               + id
               + ", conversationId="
               + conversationId
               + ", message="
               + message
               + ", date="
               + date
               + ", seen="
               + seen
               + ", sender="
               + sender
               + "]";
    }

}
