package cg.example.Assessment4.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import cg.example.Assessment4.Service.EmpService;
import cg.example.Assessment4.entities.Emp;
import cg.example.Assessment4.entities.EmpDTO;
import jakarta.validation.Valid;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/viewall")
    public String viewAll(Model model) {
        model.addAttribute("emps", service.getAll());
        return "viewall";
    }

    @GetMapping("/edit/{eid}")
    public String editForm(@PathVariable int eid, Model model) {
        Emp emp = service.getById(eid);

        EmpDTO dto = new EmpDTO();
        dto.setDeptName(emp.getDeptName());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());

        model.addAttribute("emp", dto);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveEdit(@Valid @ModelAttribute("emp") EmpDTO dto,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            return "edit";
        }

        Emp emp = new Emp();
        emp.setDeptName(dto.getDeptName());
        emp.setEmpDoj(dto.getEmpDoj());
        emp.setEmpId(dto.getEmpId());
        emp.setEmpName(dto.getEmpName());
        emp.setEmpSal(dto.getEmpSal());

        service.update(emp);

        model.addAttribute("msg", "Employee Edited Successfully");
        model.addAttribute("emps", service.getAll());

        return "viewall";
    }

    @GetMapping("/delete/{eid}")
    public String delete(@PathVariable int eid, Model model) {

        service.delete(eid);

        model.addAttribute("msg", "Employee Deleted Successfully");
        model.addAttribute("emps", service.getAll());

        return "viewall";
    }
}