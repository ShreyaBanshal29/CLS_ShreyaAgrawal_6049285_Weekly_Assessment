package cg.example.Assessment4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cg.example.Assessment4.Repo.EmpRepository;
import cg.example.Assessment4.entities.Emp;

@Service
public class EmpService {

    @Autowired
    private EmpRepository repo;

    public List<Emp> getAll() {
        return repo.findAll();
    }

    public Emp getById(int id) {
        return repo.findById(id).orElseThrow();
    }

    public void update(Emp emp) {
        repo.save(emp);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}