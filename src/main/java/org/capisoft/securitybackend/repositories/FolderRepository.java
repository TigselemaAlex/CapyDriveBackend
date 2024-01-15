package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
