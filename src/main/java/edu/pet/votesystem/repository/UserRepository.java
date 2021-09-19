package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Role;
import edu.pet.votesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("DELETE FROM User user WHERE user.id =:id")
    int delete(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE User user SET user.name =:name, user.password =:password, user.email =:email, user.role =:role, user.enable =:enable" +
            " WHERE user.id =:id")
    void update(@Param("id") Integer id,
                @Param("name") String name,
                @Param("password") String password,
                @Param("email") String email,
                @Param("role") Role role,
                @Param("enable") boolean enable);
}
