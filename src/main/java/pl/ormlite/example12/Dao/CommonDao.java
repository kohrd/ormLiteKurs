package pl.ormlite.example12.Dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example12.Model.BaseModel;
import pl.ormlite.example12.Model.Book12;

import java.sql.SQLException;
import java.util.List;

public abstract class CommonDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;


    public CommonDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }


    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) {
//    public <T extends BaseModel ,I>  void createOrUpdate(Class <T> cls, BaseModel baseModel) - przed refaktoryzacją
        // metoda ta bezie zapisywala interfejs baseModel w zaleznosci czy bedzie to autor czy ksiażka
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);  // createOr Update dodaje do bazy danych typ interfaceModel castowany na klase Ts
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class <T> cls){
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) {
        //metoda która tworzy Dao dla wszytskich klas
        // T extends Basemodel oznacza ze to dla klas które implementują taki intefejs
        // czyli metoda towrzy dao dla kazdej kalsy rozszerzającej baseModel
        // cls to klasa dla której towrzymy dao

        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

  public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls){
        // zwraca baseModel dzieki temu mozna miec typ zwracany Book lub Author bo obie klasy implementuja ten interfejs
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
  }
}
