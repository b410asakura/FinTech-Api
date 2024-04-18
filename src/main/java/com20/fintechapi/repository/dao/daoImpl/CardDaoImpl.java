package com20.fintechapi.repository.dao.daoImpl;

import com20.fintechapi.dto.cardDto.CardResponse;
import com20.fintechapi.entity.Card;
import com20.fintechapi.repository.dao.CardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardDaoImpl implements CardDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Card getByCardNumber(String cardNumber) {
        String sql = """
                select c.card_number, c.balance
                    from cards c
                where c.card_number = ?
                """;
        return jdbcTemplate.queryForObject(sql, new Object[]{cardNumber}, (rs, rowNum) -> {
            Card card = new Card();
            card.setCardNumber(rs.getString("card_number"));
            card.setBalance(rs.getDouble("balance"));
            return card;
        });
    }

    @Override
    public List<CardResponse> getAll() {
        String sql = """
                select c.id, c.card_number, c.balance, c.user_id, concat(u.first_name, ' ', u.last_name) as full_name
                from cards c
                         join users u on c.user_id = u.id;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CardResponse cardResponse = new CardResponse();
            cardResponse.setId(rs.getLong("id"));
            cardResponse.setCardNumber(rs.getString("card_number"));
            cardResponse.setBalance(rs.getDouble("balance"));
            cardResponse.setUserId(rs.getLong("user_id"));
            cardResponse.setUserFullName(rs.getString("full_name"));
            return cardResponse;
        } );
    }
}
