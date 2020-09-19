package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.FileUploadDTO;

@Repository
public interface FileUploadDAO extends JpaRepository<FileUploadDTO, Long>
{
    // @formatter:off
	@Query("SELECT fu"
		 + " FROM FileUploadDTO fu,"
	     + " UserDTO u"
		 + " WHERE u.id = :userId"
		 + " AND u.pictureFileUploadId = fu.id")
	// @formatter:on
    public FileUploadDTO findUserPictureFile(@Param("userId") Long userId);

    // @formatter:off
	@Query("SELECT fu"
		 + " FROM FileUploadDTO fu,"
	     + " ListingPictureDTO lp"
		 + " WHERE lp.listingId = :listingId"
		 + " AND lp.pictureFileUploadId = fu.id")
	// @formatter:on
    public List<FileUploadDTO> findListingPictureFiles(@Param("listingId") Long listingId);
}
