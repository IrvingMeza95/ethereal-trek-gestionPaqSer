package com.hackacode.gestionPaqSer.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity {
}
