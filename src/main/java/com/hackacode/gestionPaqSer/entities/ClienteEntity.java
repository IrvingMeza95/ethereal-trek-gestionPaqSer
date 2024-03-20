package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity {
}
