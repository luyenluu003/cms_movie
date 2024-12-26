package cms.cms.repository;

import cms.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE email = :email AND active = 1", nativeQuery = true)
    Optional<User> findUserByUsername(String email);

    @Query(value = "Select * from user where email = :username  AND active = 1", nativeQuery = true)
    User getUserInfoByUsername(@Param(value = "username") String username);

}
