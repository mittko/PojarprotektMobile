package com.example.fireextinguishinginstallationsmobile.data

import androidx.compose.runtime.mutableStateMapOf
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

val titles =
    arrayOf(
        "",
        "",
        "Входни данни",  // 0 АБОНАМЕНТНО ТЕХНИЧЕСКО ОБСЛУЖВАНЕ за м. януари 2026г.
        "Преглед на контролен панел, ел.табло - индикация",    // 1
        "Функционален тест индикация на ел. табло за управление и сработване на ПГИ", // 2
        "Преглед и тест на основно захранване", // 3
        "Преглед и тест на основна платка", // 4
        "Проверка за правилната свързаност и последователност на задействане", // 5
        "Проверка на задействието на допълнителни спомагателни съоръжения / АПУ, ръчен СК, Пускова бутилка /",// 6
        "Функционален тест на действието на звукови сигнализатори и звънци / сирени за предупреждение на пуск на гасителния агент/", // 7
        "Проверка за положението на Ръчния Спирателен Кран / СК /", // 8
        "Проверка за положението на Ръчно Пусково Устройство", // 9
        "Преглед на резервно захранване", // 10
        "Техническа проверка на лупове и линии", // 11
        "Техническа проверка на всеки един автоматичен пожароизвестителен детектор", // 12
        "Проверка за наличие на свободно пространство от 0.5 м около всеки пожароизвестителен детектор", // 13
        "Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент",// 14
        "Проверка на безпрепятствен достъп до всички ръчни пожароизвестителни бутони", // 15
        "Проверка за наличие на указателни знаци над ръчните пожароизвестителни бутони", // 16
        "Вид на съд за гасителен агент", // 17
        "Визуална проверка на съдовете за гасителен агент", // 18
        "Проверка на система за откриване на течове", // 19
        "Проверка на сигнални и изнесени устройства", // 20
        "Визуална проверка за повреди по маркучи и армировка", // 21
        "Визуална проверка за увреждания ( закрепване, корозия, механични наранявания и др. ) по тръбопроводи", // 22
        "Визуална проверка на дюзи за разпръскване на гасителен агент", // 23
        "Продухване на тръбна мрежа на ПГИ с Азот против запушване", // 24
        "Проверка за опасностите и непроницаемостта на затвореното пространство и промени, който биха могли да намалят ефикасността на инсталацията", // 25
        "Проверка за затваряне на Входна плъзгаща врата при сработване", // 26
        "Проверка за сработване на сигнална лампа СТОП ГАЗ", // 27
        "Проверка за наличие на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта", // 28
        "Проверка за функциониране на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта", // 29
        //  "Проверка за правилната подгототвка и обучение на персонала упълномощен да управлява съоръженията за гасене", //
        "Визуална проверка за структурни или експлоатационни промени в обекта, който биха попречили на работата на ПГИ", // 30
        "Проверка за наличие на актуалност на документацията, придружаваща ПГИ", // 31
        "Проверка за наличие и попълване на дневник на системата",// 32
        "Забележка"
    )

val mapOfModels =
    mutableStateMapOf<String, ArrayList<IModel>>(
        titles[0] to arrayListOf(),
        titles[1] to arrayListOf(),
        titles[2] to arrayListOf(
            // Входни данни
            FieldModel(String.format("Договор %s", numero), 0),
            FieldModel("Обект", 0),
            FieldModel("Модел и тип на инсталацията", 0),
            FieldModel("Дата на монтаж на ПГИ", 0),
            DropDownModel("Модел на автоматика за управление", 0),
            DropDownModelTwo("Вид на гасителен агент", 0),

            CountModel("Брой разпръскващи дюзи", 0),
            CountModel("Брой съдове за", 0),
            CountModel("Количество гасителен агент", 0),

            CheckedModel("Флуоросъдържащ парников газ", 0),

            FieldModel("Еквивалент в: t CO2 eq", 0),

            CountModel("Брой ръчни бутони \"СТАРТ\"", 0),
            CountModel("Брой ръчни бутони", 0),
            CountModel("Брой ръчни бутони \"ЗАРЕЖДАНЕ\"", 0),
            CountModel("Брой автоматични датчици", 0),
            CountModel("Брой АПУ", 0),
            CountModel("Брой изн. звукови сигнализатори", 0)
        ),

        titles[3] to arrayListOf(
            // Преглед на контролен панел, ел.табло - индикация
            CheckedModel("Отворен", 1),
            CheckedModel("Затворена ръчен СК", 1),
            CheckedModel("Управление Автоматично Пусково Устройство /АПУ/", 1)
        ),

        titles[4] to arrayListOf(
            //  Функционален тест индикация на ел. табло за управление и сработване на ПГИ
            CheckedModel("дисплей", 2),
            CheckedModel("пожар", 2),
            CheckedModel("светодиоди", 2),
            CheckedModel("повреда", 2),
            CheckedModel("зумер", 2),
            CheckedModel("забрана", 2),
            CheckedModelTwo("задържане", 2),
            CheckedModelTwo("тест", 2),

            ),

        titles[5] to arrayListOf(
            // Преглед и тест на основно захранване
            CheckedModelThree("Основно захранване от електрическа мрежа", 3),
            FieldModelThree("Температура на корпуса на ел.трансформатора", 3),
            CheckedModelThree("Преобразувано захранване", 3)
        ),

        titles[6] to arrayListOf(
            // Преглед и тест на основна платка
            CheckedModel("Индикация за светодиоди за повреда", 4),
            CheckedModel("Наличие на запрашеност върху ел.трансформатора", 4),
            CheckedModel("Откачени проводници,свръзки и букси", 4),
            TextModel(subTitle = "Измерено напрежение на изводите на линиите", 4),
            FieldModelThree("Линия / Кръг 1", 4),
            FieldModelThree("Линия / Кръг 2", 4)
        ),

        titles[7] to arrayListOf(
            //Проверка за правилната свързаност и последователност на задействане
            CheckedModel("", 5)
        ),
        titles[8] to arrayListOf(
            //  Проверка на задействието на допълнителни спомагателни съоръжения / АПУ, ръчен СК, Пускова бутилка /
            CheckedModel("", 6)
        ),
        titles[9] to arrayListOf(
            //Функционален тест на действието на звуков сигнализатор / сирена за предупреждение на пуск на гасителния агент
            CheckedModel("", 7)
        ),

        titles[10] to arrayListOf(
            // Проверка за положението на ръчния спирателен кран / СК /
            CheckedModel("", 8)
        ),
        titles[11] to arrayListOf(
            //Проверка за положението на "Ръчно Пъсково Устройство\
            CheckedModel(subTitle = "", position = 9)
        ),

        titles[12] to arrayListOf(
            //Преглед на резервно захранване
            CheckedModelThree("Зареждане на акумулаторни батерии", 10),
            TextModel("Акумулаторна батерия No1", 10),
            CheckedModelThree(subTitle = "Годна", 10),
            FieldModel(subTitle = "Дата на производство", 10),
            FieldModel(subTitle = "Температура", 10),
            CheckedModel("Повреда корпус", 10),
            TextModel("Акумулаторна батерия No2", 10),
            CheckedModelThree(subTitle = "Годна", 10),
            FieldModel(subTitle = "Дата на производство", 10),
            FieldModel(subTitle = "Температура", 10),
            CheckedModel(subTitle = "Повреда корпус", 10),
            CheckedModelTwo("Проверка на проводник за заземяване", 10),
            TextModel(
                subTitle = "Електрозахранване - Измерване на големината на тока в състояние на пожар и повреда с цел да се потвърди, че",
                10
            ),
            FieldModelTwo(subTitle = "Измерено", 10)
        ),

        titles[13] to arrayListOf(
            // Техническа проверка на лупове и линии
            CheckedModel("Кръг 1", 11),
            CheckedModel("Кръг 2", 11),
            FieldModel(subTitle = "", 11)
        ),

        titles[14] to arrayListOf(
            // Техническа проверка на всеки един автоматичен пожароизвестителен детектор
            CheckedModelTwo("Тест на димо-оптични детектори", 12),
            CheckedModelTwo("Тест на термични детектори", 12),
            CheckedModelTwo("Тест на термодифиренциални детектори", 12),
            CheckedModelTwo("Тест на пламъчни детектори", 12),
            CheckedModelTwo("Тест на комбинирани детектори", 12),
            CheckedModelTwo("Тест на линейно-оптични детектори", 12),
            CheckedModelTwo("Тест на Взриво защитен димен детектор", 12),
            CheckedModelTwo("Тест на радиосигнала на безжични пожароизвестителни детектори", 12)
        ),

        titles[15] to arrayListOf(
            // Проверка за наличие на свободно пространство от 0,5 м около всеки пожароизвестителен детектор

            FieldModel("", 13),
            CheckedModel("", 13),
        ),


        titles[16] to arrayListOf(
            // Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент

            CheckedModelTwo("", 14),
            CountModel(subTitle = "", 14)
        ),
        titles[17] to arrayListOf(

            CheckedModelTwo(
                //  Проверка на безпрепятствен достъп до всички ръчни пожароизвестителни бутони
                "", 15
            ),
            CountModel("", 15)
        ),

        titles[18] to arrayListOf(

            //Проверка за наличие на указателни знаци над ръчните пожароизвестителни бутони
            CheckedModelTwo("", 16),
            CountModel("", 16)
        ),

        titles[19] to arrayListOf(
            // Вид на съд за гасителен агент
            //CheckedModel("Отворен", 17),
            CheckedModel("Херметически затворен", 17)
        ),

        titles[20] to arrayListOf(
            // Визуална проверка на съдовете за гасителен агент
            ExtendedCheckedModel("", 18),
            ExtendedCheckedModel("", 18)
        ),

        titles[21] to arrayListOf(
            CheckedModel(
                //Проверка на система за откриване на течове
                "", 19
            )
        ),

        titles[22] to arrayListOf(
            CheckedModelTwo(
                // Проверка на сигнални и изнесени устройства
                "Тест на всяка една вътрешна сирена/звънец", 18
            ),
            CheckedModelTwo("Тест на всеки един изнесен индикатор", 20),
            CheckedModel(
                "Тест на устройство за препредаване на сигнал за пожар към център за управление", 20
            ), CheckedModel(
                "Тест на устройство за препредаване на сигнал за повреда към център за управление",
                20
            ), CheckedModel("Тест на командно табло на пожарната служба", 20)
        ),

        titles[23] to arrayListOf(
            // Визуална проверка за повреди по маркучи и армировка
            CheckedModel("", 21)
        ),

        titles[24] to arrayListOf(
            // Визуална проверка за увреждания (закрепване, корозия, механични наранявания и др.) по тръбопроводи
            CheckedModel(
                "", 22
            )
        ),
        titles[25] to arrayListOf(
            //  Визаулна проверка на дюзи за разпръскване на гасителен агент
            CheckedModel("", 23)
        ),
        titles[26] to arrayListOf(
            // Продухване на тръбна мрежа на ПГИ с Азот против запушване
            CheckedModel("", 24)
        ),

        titles[27] to arrayListOf(
            // Проверка за опасностите и непроницаемостта на затвореното пространство и промени, които биха могли да намалят ефективността на инсталацията
            CheckedModel("", 25)
        ),
        titles[28] to arrayListOf(
            // Проверка за затваряне на Входна плъзгаща врата при сработване
            CheckedModel("", 26)
        ),
        titles[29] to arrayListOf(
            // Проверка за сработване на сигнална лампа "СТОП ГАЗ"
            CheckedModel("", 27)
        ),
        titles[30] to arrayListOf(
            CheckedModel(
                // Проверка за наличие на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение  обекта
                "", 28
            )
        ),
        titles[31] to arrayListOf(
            // Проверка за функциониране на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта
            CheckedModel("", 29)
        ),
        titles[32] to arrayListOf(
            CheckedModel(
                // Визуална проверка за структурни или екслоатационни промени в обекта, които биха попречили на работата на ръчните бутони
                "", 30
            )
        ),
        titles[33] to arrayListOf(
            // Проверка за наличие на актуалност на документацията, придружаваща ПИС
            CheckedModel("", 31)
        ),
        titles[34] to arrayListOf(
            // Проверка за наличие и полълване на дневник на системата
            CheckedModel("", 32)
        ),
        titles[35] to arrayListOf(
            // Забележка
            FieldModel(subTitle = "", 33)
        )
    )

