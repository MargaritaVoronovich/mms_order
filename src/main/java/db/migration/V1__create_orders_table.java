package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V1__create_orders_table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        final Statement statement = context.getConnection().createStatement();

        try (statement) {
            statement.execute("create table orders" +
                    "(id bigserial not null constraint orders_pkey primary key," +
                    "created_time timestamp default current_timestamp)");
        }
    }
}
