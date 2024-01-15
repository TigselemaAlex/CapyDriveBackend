package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.File;
import org.capisoft.securitybackend.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByFolder(Folder folder);
}
