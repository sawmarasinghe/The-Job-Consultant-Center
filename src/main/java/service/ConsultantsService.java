package service;
import com.Model.Consultants;
import com.DAO.ConsultantsDAO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ConsultantsService {
    private ConsultantsDAO consultantsDAO;

    public ConsultantsService() {
        consultantsDAO = new ConsultantsDAO();
    }
    
    public List<Consultants> selectAllConsultants() {
        return consultantsDAO.selectAllConsultants();
    }

    public List<Consultants> getAllConsultants() {
        return consultantsDAO.selectAllConsultants();
    }

    public Consultants getConsultantById(int consultantId) {
        return consultantsDAO.getConsultantById(consultantId);
    }

    public void addConsultant(Consultants consultant) throws SQLException {
        consultantsDAO.addConsultant(consultant);
    }

    public void updateConsultant(Consultants consultant) throws SQLException {
        consultantsDAO.updateConsultant(consultant);
    }

    public void deleteConsultant(int consultantId) throws SQLException {
        consultantsDAO.deleteConsultant(consultantId);
    }

    public boolean validateConsultant(Consultants consultant) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Consultants>> constraintViolations = validator.validate(consultant);

        if (!constraintViolations.isEmpty()) {
            // Handle validation errors here or throw an exception
            return false;
        }

        return true;
    }
}
