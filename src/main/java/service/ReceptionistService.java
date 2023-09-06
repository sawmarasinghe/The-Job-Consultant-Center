package service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.ReceptionistDAO;
import com.Model.Receptionist;

public class ReceptionistService {

    private ReceptionistDAO receptionistDAO;

    public ReceptionistService() {
        receptionistDAO = new ReceptionistDAO();
    }

    public void addReceptionist(Receptionist receptionist) throws SQLException {
        receptionistDAO.addReceptionist(receptionist);
    }

    public Receptionist getReceptionistById(int receptionistId) {
        return receptionistDAO.getReceptionistById(receptionistId);
    }

    public List<Receptionist> getAllReceptionists() {
        return receptionistDAO.selectAllReceptionists();
    }

    public boolean deleteReceptionist(int receptionistId) throws SQLException {
        return receptionistDAO.deleteReceptionist(receptionistId);
    }

    public boolean updateReceptionist(Receptionist receptionist) throws SQLException {
        return receptionistDAO.updateReceptionist(receptionist);
    }
}