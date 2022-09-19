package com.example.introdTestSpringBootMockBean;

import com.example.introdTestSpringBootMockBean.model.BookingModel;
import com.example.introdTestSpringBootMockBean.repository.BookingRepository;
import com.example.introdTestSpringBootMockBean.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @TestConfiguration
    static class BookingServiceConfiguration
    {
        @Bean
        public BookingService bookingService()
        {
            return new BookingService();
        }
    }

    // ponto de injeção
    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator()
    {
        String name = "Wellison";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Before
    public void setup()
    { // prepara através de um mock um repository, afim de isolar o teste UNITÀRIO apenas neste escopo
        // Toda vez que for preciso um repository ele retorna através do mock

        LocalDate checkIn = LocalDate.parse("2022-09-19");
        LocalDate checkOut = LocalDate.parse("2022-09-29"); // 10 dias depois
        BookingModel bookingmodel = new BookingModel("1", "Wellison", checkIn, checkOut, 2);

        Mockito.when(bookingRepository.findByReserveName(bookingmodel.getReserveName()))
                .thenReturn(Optional.of(bookingmodel));
    }
}
