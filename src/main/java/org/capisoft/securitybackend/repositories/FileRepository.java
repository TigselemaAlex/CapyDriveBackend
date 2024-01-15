package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.File;
import org.capisoft.securitybackend.entities.Folder;
import org.capisoft.securitybackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByFolderAndStudent(Folder folder, Student student);
}
