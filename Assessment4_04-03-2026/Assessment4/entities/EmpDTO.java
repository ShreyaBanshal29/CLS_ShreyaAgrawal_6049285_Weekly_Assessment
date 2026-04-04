package cg.example.Assessment4.entities;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EmpDTO {

    private Integer empId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z]{3,25}$",
             message = "Name must be alphabets (3-25 chars)")
    private String empName;

    @Min(value = 1000, message = "Salary must be >= 1000")
    @Max(value = 500000, message = "Salary must be <= 500000")
    private Double empSal;

    @FutureOrPresent(message = "Date must be current or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate empDoj;

    @Pattern(regexp = "^(hr|production)$",
             message = "Department must be hr or production")
    private String deptName;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}

	public LocalDate getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(LocalDate empDoj) {
		this.empDoj = empDoj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public EmpDTO(Integer empId,
			@NotBlank(message = "Name is required") @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "Name must be alphabets (3-25 chars)") String empName,
			@Min(value = 1000, message = "Salary must be >= 1000") @Max(value = 500000, message = "Salary must be <= 500000") Double empSal,
			@FutureOrPresent(message = "Date must be current or future") LocalDate empDoj,
			@Pattern(regexp = "^(hr|production)$", message = "Department must be hr or production") String deptName) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSal = empSal;
		this.empDoj = empDoj;
		this.deptName = deptName;
	}

	public EmpDTO() {
		// TODO Auto-generated constructor stub
	}

   
}