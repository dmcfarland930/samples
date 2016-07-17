package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice - Daniel McFarland
 */
public class OrderDaoDbImpl implements OrderDao {

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (customer_name, product_id, tax_id, area, order_date, order_total, material_total, labor_total, tax_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET customer_name = ?, product_id = ?, tax_id = ?, area = ?, order_date = ?, order_total = ?, material_total = ?, labor_total = ?, tax_total = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDER = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDER_LIST = "SELECT * FROM orders";
    private static final String SQL_GET_ORDER_LIST_DATE = "SELECT * FROM orders WHERE order_date = ?";

    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;
    private TaxesDao taxDao;

    public OrderDaoDbImpl(JdbcTemplate jdbcTemplate, ProductDao productDao, TaxesDao taxDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order create(Order orders) {

        jdbcTemplate.update(SQL_INSERT_ORDER,
                orders.getCustomerName(),
                orders.getProduct().getId(),
                orders.getTaxes().getId(),
                orders.getArea(),
                orders.getDate(),
                orders.getOrderTotal(),
                orders.getMaterialCost(),
                orders.getTotalLaborCost(),
                orders.getTax());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        orders.setOrderNumber(id);

        return orders;
    }

    @Override
    public void update(Order order) {

        jdbcTemplate.update(SQL_UPDATE_ORDER,
                order.getCustomerName(),
                order.getProduct().getId(),
                order.getTaxes().getId(),
                order.getArea(),
                order.getDate(),
                order.getOrderTotal(),
                order.getMaterialCost(),
                order.getTotalLaborCost(),
                order.getTax(),
                order.getOrderNumber());

    }

    @Override
    public void delete(Order orders) {

        jdbcTemplate.update(SQL_DELETE_ORDER,
                orders.getOrderNumber());

    }

    @Override
    public Order get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_ORDER, new OrderMapper(), id);

    }

    @Override
    public List<Order> getList() {

        return jdbcTemplate.query(SQL_GET_ORDER_LIST, new OrderMapper());

    }

    @Override
    public double calculateOrderTotal(double laborTotal, double productTotal, double tax) {

        double total;

        total = laborTotal + productTotal + tax;

        return total;
    }

    @Override
    public List<Order> getOrdersOnDate(String date) {


        List<Order> orders = jdbcTemplate.query(SQL_GET_ORDER_LIST_DATE, new OrderMapper(), date);


        return orders;

    }

    private final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {

            Order orders = new Order();

            orders.setOrderNumber(rs.getInt("id"));
            orders.setCustomerName(rs.getString("customer_name"));
            orders.setProduct(productDao.get(rs.getInt("product_id")));
            orders.setTaxes(taxDao.get(rs.getInt("tax_id")));
            orders.setArea(rs.getDouble("area"));
            orders.setDate(rs.getDate("order_date"));
            orders.setOrderTotal(rs.getDouble("order_total"));
            orders.setMaterialCost(rs.getDouble("material_total"));
            orders.setTax(rs.getDouble("tax_total"));
            orders.setTotalLaborCost(rs.getDouble("labor_total"));

            return orders;

        }

    }

}
