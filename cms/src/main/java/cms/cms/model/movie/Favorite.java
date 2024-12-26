package cms.cms.model.movie;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "favorite")
public class Favorite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="user_phone")
    private String user_phone;

    @Column(name ="movie_code")
    private String movie_code;

    @Column(name ="added_ad")
    private Date date;

}
