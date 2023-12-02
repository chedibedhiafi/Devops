import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @Test
    void retrieveStockTest() {
        Stock stock  = new Stock();
        when(stockRepository.findById(stock.getIdStock())).thenReturn(Optional.of(stock));
        assertEquals(stock.getIdStock(), stockService.retrieveStock(stock.getIdStock()).getIdStock());
    }
    @Test
    void retrieveAllStockTest() {
        when(stockRepository.findAll()).thenReturn(Stream.of(new Stock(), new Stock(), new Stock()).collect(Collectors.toList()));
        assertEquals(3, stockService.retrieveAllStock().size());
    }

    @Test
    void addStockSectorTest() {
        Stock  stock  = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        assertEquals(stock, stockService.addStock(stock));
    }
    @Test
    void deleteStockTest() {
        Stock stock = new Stock();
        Stock stock1 = new Stock();
        stockService.deleteStock(stock.getIdStock()); // Use operator.getId() to get the ID
        stockService.deleteStock(stock1.getIdStock()); // Use operator1.getId() to get the ID
        verify(stockRepository, times(2)).deleteById(stock.getIdStock());
    }
    @Test
    void updateStockTest() {
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        assertEquals(stock, stockService.updateStock(stock));
    }


}