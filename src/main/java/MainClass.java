
import megacalss.SuperClass;
import parsers.*;
import parsers.another.CalculatorClass;
import parsers.another.Cars;
import parsers.another.Services;
import parsers.another.Wheels;
import writers.ToExcelWriter;

public class MainClass {
    public static void main(String[] args) throws Exception {



        Wheels wheels = new Wheels();
        wheels.getWheelsCarAlloy();
        wheels.getWheelsCarSteel();
        wheels.getWheelsSuvAlloy();
        wheels.getWheelsSuvSteel();
        wheels.getWheelsVan4();



        Services services = new Services();
        services.getServices1();
        services.getServices2();
        services.getServices3();
        services.getServices4();
        services.getServices5();
        services.getServices6();
        services.getServices7();
        services.getServices8();
        services.getServices9();
        services.getServices10();
        services.getServices11();
        services.getServices15();
//
//
        Cars cars = new Cars();
        cars.brands();
        cars.brandModels();
        cars.brandsModelsSeries();
//
////
        SuperClass superClass = new SuperClass();
        superClass.amortization();
        superClass.boosts();
        superClass.condisioner();
        superClass.diagnostic();
        superClass.diezel();
        superClass.electicity();
        superClass.engine();
        superClass.freezer();
        superClass.rule();
        superClass.techObs();
        superClass.transmission();
        superClass.turbo();


        CalculatorClass calculatorClass = new CalculatorClass();
        calculatorClass.getServiceByUnit();
//        calculatorClass.getServiceByUnitSecondTry();



//------------------------------------------------------
//------------------------------------------------------
//------------------------------------------------------

//
//        ToExcelWriter toExcelWriter = new ToExcelWriter();
//        toExcelWriter.WriteToExcel();

//        Страница Техническое обслуживание
//        ConnectorClass connectorClass = new ConnectorClass();
//        connectorClass.sendGet();

//        Страница Диагностика
//        DiagnosticClass diagnosticClass = new DiagnosticClass();
//        diagnosticClass.sendGet();

//        Страница Ремонт подвесок
//        AmortizationClass amortizationClass = new AmortizationClass();
//        amortizationClass.sendGet();

//        Страница Ремонт тормозной системы
//        BoostClass boostClass = new BoostClass();
//        boostClass.sendGet();

//        Страница Ремонт системы охлаждения
//        FreezClass freezClass = new FreezClass();
//        freezClass.sendGet();

//        Страница Ремонт рулевого управления
//        RuleClass ruleClass = new RuleClass();
//        ruleClass.sendGet();

//        Страница Ремонт выхлопной системы
//        TurboClass turboClass = new TurboClass();
//        turboClass.sendGet();

//        Страница Ремонт топливной системы
//        DiezelClass diezelClass = new DiezelClass();
//        diezelClass.sendGet();

//        Страница Ремонт трансмиссии
//        TransmissionClass transmissionClass = new TransmissionClass();
//        transmissionClass.sendGet();

//        Страница Ремонт двигателя
//        EngineClass engineClass = new EngineClass();
//        engineClass.sendGet();

//        Страница Ремонт электрооборудования
//        ElectricClass electricClass = new ElectricClass();
//        electricClass.sendGet();


//        Страница Стоимость малярно-кузовных работ
//--------------------------

//        Страница Ремонт и замена автомобильных стекол
//--------------------------

//        Страница Услуги по ремонту развала-схождения автомобиля
//--------------------------

//        Страница шиномонтаж
//        ссылка открыта


//        Страница Ремонт и обслуживание автомобильных кондиционеров
//        ConditionerClass conditionerClass = new ConditionerClass();
//        conditionerClass.sendGet();

//        Ремонт форсунок и ТНВД
//--------------------------

//        Мойка автомобилей
//--------------------------

//        Услуги эвакуатора
//--------------------------

//        Акции
//--------------------------




    }
}
