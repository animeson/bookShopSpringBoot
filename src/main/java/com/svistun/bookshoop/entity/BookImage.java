package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_image")
@Accessors(chain = true)
public class BookImage {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @JoinColumn(
            name = "book_image_id"
    )
    private Long bookImageID;
    private String filename;
    private String path;
    private String mimeType;
    private Long size;
    private LocalDateTime dataTimeUploads;
    private Boolean isPrimary;
    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "book_id"
    )
    private Collection<Book> book;

}
