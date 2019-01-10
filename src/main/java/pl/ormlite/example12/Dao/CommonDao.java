package pl.ormlite.example12.Dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example12.Model.BaseModel;

import java.sql.SQLException;

public abstract class CommonDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;


    public CommonDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }



    public <T extends BaseModel ,I>  void createOrUpdate(Class <T> cls, BaseModel baseModel){
        // metoda ta bezie zapisywala interfejs baseModel w zaleznosci czy bedzie to autor czy ksiażka
        Dao<T, I> dao = getDao(cls);
        try {
            dao.createOrUpdate((T) baseModel);  // createOr Update dodaje do bazy danych typ interfaceModel castowany na klase Ts
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }


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
}
