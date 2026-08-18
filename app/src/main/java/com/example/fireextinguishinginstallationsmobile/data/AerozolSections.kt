package com.example.fireextinguishinginstallationsmobile.data

import com.example.fireextinguishinginstallationsmobile.interfaces.IModel
import com.example.fireextinguishinginstallationsmobile.models.CheckedModel
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelThree
import com.example.fireextinguishinginstallationsmobile.models.CheckedModelTwo
import com.example.fireextinguishinginstallationsmobile.models.CountModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModel
import com.example.fireextinguishinginstallationsmobile.models.DropDownModelTwo
import com.example.fireextinguishinginstallationsmobile.models.ExtendedCheckedModel
import com.example.fireextinguishinginstallationsmobile.models.FieldModel
import com.example.fireextinguishinginstallationsmobile.models.FieldModelThree
import com.example.fireextinguishinginstallationsmobile.models.FieldModelTwo
import com.example.fireextinguishinginstallationsmobile.models.TextModel
import com.example.fireextinguishinginstallationsmobile.numero

val aerozolSections =
    arrayOf(
        "Камера",
        "Входни данни",
        "Преглед и тест на основно захранване",
        "Преглед и тест на основна платка",
        "Преглед на резервно захранване",
        "Преглед на контролен панел, ел.табло - индикация",
        "Функционален тест индикация на ел. табло за управление и сработване на ПГИ",
        "Проверка за правилната свързаност и последователност на задействане",
        "Функционален тест на действието на звукови сигнализатори и звънци / сирени за предупреждение на пуск на гасителния агент/",
        "Техническа проверка на лупове и линии",
        "Техническа проверка на всеки един автоматичен пожароизвестителен детектор",
        "Проверка за наличие на свободно пространство от 0.5 м около всеки пожароизвестителен детектор",
        "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент",
        "Проверка на безпрепятствен достъп до всички ръчни бутони",
        "Проверка за наличие на указателни знаци над ръчните бутони",
        "Визуална проверка на аерозолен генератор (за гасителен агент)",
        "Проверка на сигнални и изнесени устройства",

        "Проверка за наличие на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта",
        "Проверка за функциониране на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта",
        "Визуална проверка за структурни или експлоатационни промени в обекта, който биха попречили на работата на ПГИ",
        "Проверка за наличие на актуалност на документацията, придружаваща ПГИ",
        "Проверка за наличие и попълване на дневник на системата",
        "Забележка"
    )

val aerozolMap = mapOf<String, ArrayList<IModel>>(
    aerozolSections[0] to arrayListOf(),
    aerozolSections[1] to arrayListOf(
        // Входни данни
        CountModel("Номер на обект"),
        FieldModel("Баркод"),
        FieldModel(String.format("Договор %s", numero)),
        DropDownModel("Обект"),
      //  DropDownModel("Модел и тип на инсталацията"), ATENTION !!!
        FieldModel("Дата на монтаж на ПГИ"),
        DropDownModel("Модел на автоматика за управление"),
        DropDownModel("Вид на гасителен агент"),

        CountModel("Брой разпръскващи дюзи"),
      //  CountModel("Брой съдове за"),
      //  CountModel("Количество гасителен агент"),
      //  CheckedModel("Флуоросъдържащ парников газ"),
      //    FieldModel("Еквивалент в: t CO2 eq"),

        CountModel("Брой ръчни бутони \"СТАРТ\""),
        CountModel("Брой ръчни бутони"), //  СТОП
        CountModel("Брой ръчни бутони \"ЗАРЕЖДАНЕ\""), // ЗАДЪРЖАНЕ
        CountModel("Брой автоматични датчици"),
      //  CountModel("Брой АПУ"),
        CountModel("Брой изн. звукови сигнализатори")
    ),
    aerozolSections[2] to arrayListOf(
        // Преглед и тест на основно захранване
        CheckedModelThree("Основно захранване от електрическа мрежа"),
        FieldModelThree("Температура на корпуса на ел.трансформатора"),
        CheckedModelThree("Преобразувано захранване")
    ),

    aerozolSections[3] to arrayListOf(
        // Преглед и тест на основна платка
        CheckedModel("Индикация за светодиоди за повреда"),
        CheckedModel("Наличие на запрашеност върху ел.трансформатора"),
        CheckedModel("Откачени проводници,свръзки и букси"),
        TextModel(subTitle = "Измерено напрежение на изводите на линиите"),
        FieldModelThree("Линия / Кръг 1"),
        FieldModelThree("Линия / Кръг 2"),
        FieldModelThree("Линия / Кръг 3")
    ),
    aerozolSections[4] to arrayListOf(
        //Преглед на резервно захранване
        CheckedModelThree("Зареждане на акумулаторни батерии"),
        TextModel("Акумулаторна батерия No1"),
        CheckedModelThree(subTitle = "Годна"),
        FieldModel(subTitle = "Дата на производство"),
        FieldModel(subTitle = "Температура"),
        CheckedModel("Повреда корпус"),
        TextModel("Акумулаторна батерия No2"),
        CheckedModelThree(subTitle = "Годна"),
        FieldModel(subTitle = "Дата на производство"),
        FieldModel(subTitle = "Температура"),
        CheckedModel(subTitle = "Повреда корпус"),
        CheckedModelTwo("Проверка на проводник за заземяване"),
        TextModel(
            subTitle = "Електрозахранване - Измерване на големината на тока в състояние на пожар и повреда с цел да се потвърди, че",
        ),
        FieldModelTwo(subTitle = "Измерено")
    ),
    aerozolSections[5] to arrayListOf(
        // Преглед на контролен панел, ел.табло - индикация
        CheckedModel("Ръчен режим"),
        CheckedModel("Автоматичен режим"),
        CheckedModel("Управление Автоматично Пусково Устройство /АПУ/")
    ),

    aerozolSections[6] to arrayListOf(
        //  Функционален тест индикация на ел. табло за управление и сработване на ПГИ
        CheckedModel("дисплей"),
        CheckedModel("пожар"),
        CheckedModel("светодиоди"),
        CheckedModel("повреда"),
        CheckedModel("зумер"),
        CheckedModel("забрана"),
        CheckedModelTwo("задържане"),
        CheckedModelTwo("тест"),

        ),



    aerozolSections[7] to arrayListOf(
        //Проверка за правилната свързаност и последователност на задействане
        CheckedModel("")
    ),
    aerozolSections[8] to arrayListOf(
        //Функционален тест на действието на звуков сигнализатор / сирена за предупреждение на пуск на гасителния агент
        CheckedModel("Сирена"),
        CheckedModel("Звънец")
    ),


    aerozolSections[9] to arrayListOf(
        // Техническа проверка на лупове и линии
        CheckedModel("Линия / Кръг 1"),
        CheckedModel("Линия / Кръг 2"),
        CheckedModel("Линия / Кръг 3"),
        FieldModel(subTitle = "")
    ),

    aerozolSections[10] to arrayListOf(
        // Техническа проверка на всеки един автоматичен пожароизвестителен детектор
        CheckedModelTwo("Тест на димо-оптични детектори"),
        CheckedModelTwo("Тест на термични детектори"),
        CheckedModelTwo("Тест на взривозащитен водороден анализатор"),
        CheckedModelTwo("Тест на взривозащитен CO анализатор"),
     //   CheckedModelTwo("Тест на комбинирани детектори"),
     //   CheckedModelTwo("Тест на линейно-оптични детектори"),
     //   CheckedModelTwo("Тест на Взриво защитен димен детектор"),
     //   CheckedModelTwo("Тест на радиосигнала на безжични пожароизвестителни детектори")
    ),

    aerozolSections[11] to arrayListOf(
        // Проверка за наличие на свободно пространство от 0,5 м около всеки пожароизвестителен детектор

        FieldModel(""),
        CheckedModel(""),
    ),


    aerozolSections[12] to arrayListOf(
        // Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент
        CountModel(subTitle = ""),
        CheckedModelTwo("")

    ),
    aerozolSections[13] to arrayListOf(

        CheckedModelTwo(
            //  Проверка на безпрепятствен достъп до всички ръчни пожароизвестителни бутони
            ""
        ),
        CountModel("")
    ),

    aerozolSections[14] to arrayListOf(

        //Проверка за наличие на указателни знаци над ръчните пожароизвестителни бутони
        CheckedModelTwo(""),
        CountModel("")
    ),

    aerozolSections[15] to arrayListOf(
        // Визуална проверка на аерозолен генератор (за гасителен агент)
        ExtendedCheckedModel(""),
        ExtendedCheckedModel(""),
        ExtendedCheckedModel(""),
        ExtendedCheckedModel(""),
        ExtendedCheckedModel(""),
        ExtendedCheckedModel("")
    ),

    aerozolSections[16] to arrayListOf(
        CheckedModelTwo(
            // Проверка на сигнални и изнесени устройства
            "Тест на всяка една вътрешна сирена/звънец"
        ),
        CheckedModelTwo("Тест на всеки един изнесен индикатор"),
        CheckedModel(
            "Тест на устройство за препредаване на сигнал за пожар към център за управление"
        ), CheckedModel(
            "Тест на устройство за препредаване на сигнал за повреда към център за управление",
        ), CheckedModel("Тест на командно табло на пожарната служба")
    ),



    aerozolSections[17] to arrayListOf(
        CheckedModel(
            // Проверка за наличие на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение  обекта
            ""
        )
    ),
    aerozolSections[18] to arrayListOf(
        // Проверка за функциониране на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта
        CheckedModel("")
    ),

    aerozolSections[19] to arrayListOf(
        CheckedModel(
            // Визуална проверка за структурни или екслоатационни промени в обекта, които биха попречили на работата на ПГИ
            ""
        )
    ),
    aerozolSections[20] to arrayListOf(
        // Проверка за наличие на актуалност на документацията, придружаваща ПГИ
        CheckedModel("")
    ),
    aerozolSections[21] to arrayListOf(
        // Проверка за наличие и полълване на дневник на системата
        CheckedModel("")
    ),
    aerozolSections[22] to arrayListOf(
        // Забележка
        FieldModel(subTitle = "")


    ))