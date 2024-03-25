//package Scenarios;
//
//import Database.Repositories.AccountRepository;
//import Database.AppConfig;
//import MyExceptions.ShortageOfFundsException;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.math.BigDecimal;
//
//public class AccountScenario
//{
//
//    private final AccountRepository _repository;
//
//    public AccountScenario()
//    {
//        var context = new AnnotationConfigApplicationContext();
//
//        context.register(AccountRepository.class);
//        context.register(AppConfig.class);
//        context.refresh();
//
//        _repository = context.getBean(AccountRepository.class);
//    }
//
//    public BigDecimal Withdrawal(BigDecimal amount, Integer accountId) throws ShortageOfFundsException
//    {
//        return _repository.Withdrawal(amount, accountId);
//    }
//}
