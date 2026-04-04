package cg.example.Assessment4.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cg.example.Assessment4.entities.Emp;

public interface EmpRepository extends JpaRepository<Emp,Integer> {

}
