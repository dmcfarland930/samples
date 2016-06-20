/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrderDaoDbImpl implements OrderDao {

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (customer_name, product_id, tax_id, area, order_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET customer_name = ?, product_id = ?, tax_id = ?, area = ?, order_date = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDER = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDER_LIST = "SELECT * FROM orders";

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
                orders.getDate());

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

        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        List<Order> orders = getList();
        List<Order> ordersMatchDate = new ArrayList();
        for (Order order : orders) {

            String orderDate = sdf.format(order.getDate());

            if (date.equals(orderDate)) {

                ordersMatchDate.add(order);

            }

        }

        return ordersMatchDate;

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

            return orders;

        }

    }

}
