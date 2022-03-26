package com.gyugyu.jpaprogramming;

import com.gyugyu.jpaprogramming.model.chap5.Team;
import com.gyugyu.jpaprogramming.model.chap5.TeamMember;
import com.gyugyu.jpaprogramming.model.chap5.TeamMemberRepository;
import com.gyugyu.jpaprogramming.model.chap5.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class Chap5Test {

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Team team = Team.builder()
                        .id("team1")
                        .name("팀1")
                        .build();
        entityManager.persist(team);

        TeamMember member1 = TeamMember.builder()
                                       .id("member1")
                                       .username("회원1")
                                       .team(team)
                                       .build();
        entityManager.persist(member1);

        TeamMember member2 = TeamMember.builder()
                                       .id("member2")
                                       .username("회원2")
                                       .team(team)
                                       .build();
        entityManager.persist(member2);
    }

    @Test
    void testFind() {
        var teamMember = entityManager.find(TeamMember.class, "member1");
        assertEquals("회원1", teamMember.getUsername());
        assertEquals("팀1", teamMember.getTeam().getName());
    }

    @Test
    void testJpql() {
        var jpql = "select m from TeamMember m join m.team t where t.name=:teamName";
        List<TeamMember> teamMembers = entityManager.createQuery(jpql, TeamMember.class)
                                                    .setParameter("teamName", "팀1")
                                                    .getResultList();

        for (TeamMember teamMember : teamMembers) {
            System.out.println(teamMember);
        }

        assertThat(teamMembers.stream().map(TeamMember::getUsername)).containsAnyOf("회원1", "회원2");
    }

    @Test
    void testUpdateRelation() {
        Team team2 = Team.builder()
                         .id("team2")
                         .name("팀2")
                         .build();
        entityManager.persist(team2);

        TeamMember member = entityManager.find(TeamMember.class, "member1");
        member.changeTeam(team2);

        var member1 = entityManager.find(TeamMember.class, "member1");
        assertEquals("team2", member1.getTeam().getId());
    }

    @Test
    void testBiDirection() {
        var member1 = entityManager.find(TeamMember.class, "member1");
        System.out.println(member1);

        Team team = entityManager.find(Team.class, "team1");
        System.out.println(team);
    }

    @Test
    void test() {
    }
}
