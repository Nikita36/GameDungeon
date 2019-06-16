package database.services;

import database.DAO.LogDAO;
import database.entity.Log;

import java.util.List;

public class LogService {
    private static database.DAO.LogDAO LogDAO;

    public LogService() {
        LogDAO = new LogDAO();
    }

    public void save(Log entity) {
        LogDAO.openCurrentSessionwithTransaction();
        LogDAO.save(entity);
        LogDAO.closeCurrentSessionwithTransaction();
    }

    public void update(Log entity) {
        LogDAO.openCurrentSessionwithTransaction();
        LogDAO.update(entity);
        LogDAO.closeCurrentSessionwithTransaction();
    }

    public Log findById(String id) {
        LogDAO.openCurrentSession();
        Log Log = LogDAO().findById(id);
        LogDAO.closeCurrentSession();
        return Log;
    }

    public void delete(String id) {
        LogDAO.openCurrentSessionwithTransaction();
        Log Log = LogDAO().findById(id);
        LogDAO.delete(Log);
        LogDAO.closeCurrentSessionwithTransaction();
    }

    public List<Log> findAll() {
        LogDAO.openCurrentSession();
        List<Log> Logs = LogDAO.findAll();
        LogDAO.closeCurrentSession();
        return Logs;
    }

    public void deleteAll() {
        LogDAO.openCurrentSessionwithTransaction();
        LogDAO.deleteAll();
        LogDAO.closeCurrentSessionwithTransaction();
    }

    public LogDAO LogDAO() {
        return LogDAO;
    }
}

