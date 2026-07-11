package com.mamata.employee_service.specification;

import com.mamata.employee_service.dto.EmployeeSearchRequest;
import com.mamata.employee_service.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class EmployeeSpecification {

    public static Specification<Employee> search(EmployeeSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null &&
                    !request.getName().isBlank()) {

                predicates.add(

                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + request.getName().toLowerCase() + "%"
                        )
                );
            }
            if (request.getDepartment() != null && !request.getDepartment().isBlank()) {
                predicates.add(
                       cb.equal(
                                root.get("department"),
                                request.getDepartment())
                );

            }
            if (request.getEmail() != null &&
                    !request.getEmail().isBlank()) {

                predicates.add(

                      cb.equal(
                                root.get("email"),
                                request.getEmail()
                        )
                );
            }

            if (request.getMinSalary() != null) {

                predicates.add(

                        (Predicate) cb.greaterThanOrEqualTo(
                                root.get("salary"),
                                request.getMinSalary()
                        )
                );
            }
            if (request.getMaxSalary() != null) {

                predicates.add(

                        cb.lessThanOrEqualTo(
                                root.get("salary"),
                                request.getMaxSalary()
                        )
                );
            }
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));

        };

    }
}
