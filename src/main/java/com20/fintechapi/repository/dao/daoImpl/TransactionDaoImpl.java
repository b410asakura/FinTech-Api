package com20.fintechapi.repository.dao.daoImpl;

import com20.fintechapi.dto.transactionDto.TransactionResponse;
import com20.fintechapi.enums.Currency;
import com20.fintechapi.enums.TransactionType;
import com20.fintechapi.repository.dao.TransactionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionDaoImpl implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<TransactionResponse> getAll() {
        String sql = """
                select t.id,
                       t.amount,
                       t.currency,
                       t.fee,
                       t.fee_amount,
                       t.transaction_type,
                       t.date_time,
                       source.card_number as source_card,
                       destination.card_number as destination_card
                from transactions t
                join cards source on t.source_card_id = source.id
                join cards destination on t.destination_card_id = destination.id
                """;
        return jdbcTemplate.query(sql, ((rs, rowNum) -> {
            new TransactionResponse();
            return TransactionResponse.builder()
                    .id(rs.getLong("id"))
                    .amount(rs.getDouble("amount"))
                    .currency(Currency.valueOf(rs.getString("currency")))
                    .fee(rs.getInt("fee"))
                    .feeAmount(rs.getDouble("fee_amount"))
                    .transactionType(TransactionType.valueOf(rs.getString("transaction_type")))
                    .dateTime(rs.getTimestamp("date_time").toInstant().atZone(ZoneId.systemDefault()))
                    .sourceCard(rs.getString("source_card"))
                    .destinationCard(rs.getString("destination_card"))
                    .build();
        }));

    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        String sql = "";
        return null;
    }
}
