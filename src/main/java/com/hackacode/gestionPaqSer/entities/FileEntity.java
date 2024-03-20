package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "imagenes")
public class FileEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_imagen")
    private String id;
    private String name;
    private String type;
    @Column(length = 5242880)
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] data;
}
