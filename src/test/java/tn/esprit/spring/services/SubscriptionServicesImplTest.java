package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubscriptionServicesImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setPrice(100.0f);
        subscription.setTypeSub(TypeSubscription.MONTHLY);
    }

    @Test
    void addSubscriptionTest() {
        // Mock the behavior of subscriptionRepository.save()
        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);

        // Call the method under test
        Subscription savedSubscription = subscriptionService.addSubscription(subscription);

        // Assertions
        assertThat(savedSubscription).isNotNull();
        assertThat(savedSubscription.getEndDate()).isEqualTo(subscription.getStartDate().plusMonths(1));
    }

    @Test
    void updateSubscriptionTest() {
        // Mock the behavior of subscriptionRepository.save()
        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);

        // Call the method under test
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);

        // Assertions
        assertThat(updatedSubscription).isNotNull();
    }

    @Test
    void retrieveSubscriptionByIdTest() {
        // Mock the behavior of subscriptionRepository.findById()
        when(subscriptionRepository.findById(1L)).thenReturn(java.util.Optional.of(subscription));

        // Call the method under test
        Subscription retrievedSubscription = subscriptionService.retrieveSubscriptionById(1L);

        // Assertions
        assertThat(retrievedSubscription).isNotNull();
        assertThat(retrievedSubscription.getNumSub()).isEqualTo(1L);
    }

    @Test
    void getSubscriptionByTypeTest() {
        // Mock the behavior of subscriptionRepository.findByTypeSubOrderByStartDateAsc()
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.MONTHLY))
                .thenReturn(new HashSet<>());

        // Call the method under test
        Set<Subscription> subscriptions = subscriptionService.getSubscriptionByType(TypeSubscription.MONTHLY);

        // Assertions
        assertThat(subscriptions).isNotNull();
    }

    @Test
    void retrieveSubscriptionsByDatesTest() {
        // Mock the behavior of subscriptionRepository.getSubscriptionsByStartDateBetween()
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);

        when(subscriptionRepository.getSubscriptionsByStartDateBetween(LocalDate.now(), LocalDate.now().plusMonths(1)))
                .thenReturn(subscriptionList);

        // Call the method under test
        List<Subscription> subscriptions = subscriptionService.retrieveSubscriptionsByDates(LocalDate.now(), LocalDate.now().plusMonths(1));

        // Assertions
        assertThat(subscriptions).isNotNull();
        assertThat(subscriptions).hasSize(1);
    }
}