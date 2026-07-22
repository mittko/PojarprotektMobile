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
        "Камера",
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

val dataMap = mapOf<String, ArrayList<IModel>>(

        titles[0] to arrayListOf(),
        titles[1] to arrayListOf(
            // Входни данни
            FieldModel("Номер на обект"),
            FieldModel("Баркод"),
            FieldModel(String.format("Договор %s", numero)),
            FieldModel("Обект"),
            FieldModel("Модел и тип на инсталацията"),
            FieldModel("Дата на монтаж на ПГИ"),
            DropDownModel("Модел на автоматика за управление"),
            DropDownModelTwo("Вид на гасителен агент"),

            CountModel("Брой разпръскващи дюзи"),
            CountModel("Брой съдове за"),
            CountModel("Количество гасителен агент"),

            CheckedModel("Флуоросъдържащ парников газ"),

            FieldModel("Еквивалент в: t CO2 eq"),

            CountModel("Брой ръчни бутони \"СТАРТ\""),
            CountModel("Брой ръчни бутони"),
            CountModel("Брой ръчни бутони \"ЗАРЕЖДАНЕ\""),
            CountModel("Брой автоматични датчици"),
            CountModel("Брой АПУ"),
            CountModel("Брой изн. звукови сигнализатори")
        ),

        titles[2] to arrayListOf(
            // Преглед на контролен панел, ел.табло - индикация
            CheckedModel("Отворен"),
            CheckedModel("Затворена ръчен СК"),
            CheckedModel("Управление Автоматично Пусково Устройство /АПУ/")
        ),

        titles[3] to arrayListOf(
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

        titles[4] to arrayListOf(
            // Преглед и тест на основно захранване
            CheckedModelThree("Основно захранване от електрическа мрежа"),
            FieldModelThree("Температура на корпуса на ел.трансформатора"),
            CheckedModelThree("Преобразувано захранване")
        ),

        titles[5] to arrayListOf(
            // Преглед и тест на основна платка
            CheckedModel("Индикация за светодиоди за повреда"),
            CheckedModel("Наличие на запрашеност върху ел.трансформатора"),
            CheckedModel("Откачени проводници,свръзки и букси"),
            TextModel(subTitle = "Измерено напрежение на изводите на линиите"),
            FieldModelThree("Линия / Кръг 1"),
            FieldModelThree("Линия / Кръг 2")
        ),

        titles[6] to arrayListOf(
            //Проверка за правилната свързаност и последователност на задействане
            CheckedModel("")
        ),
        titles[7] to arrayListOf(
            //  Проверка на задействието на допълнителни спомагателни съоръжения / АПУ, ръчен СК, Пускова бутилка /
            CheckedModel("")
        ),
        titles[8] to arrayListOf(
            //Функционален тест на действието на звуков сигнализатор / сирена за предупреждение на пуск на гасителния агент
            CheckedModel("")
        ),

        titles[9] to arrayListOf(
            // Проверка за положението на ръчния спирателен кран / СК /
            CheckedModel("")
        ),
        titles[10] to arrayListOf(
            //Проверка за положението на "Ръчно Пъсково Устройство\
            CheckedModel(subTitle = "")
        ),

        titles[11] to arrayListOf(
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

        titles[12] to arrayListOf(
            // Техническа проверка на лупове и линии
            CheckedModel("Кръг 1"),
            CheckedModel("Кръг 2"),
            FieldModel(subTitle = "")
        ),

        titles[13] to arrayListOf(
            // Техническа проверка на всеки един автоматичен пожароизвестителен детектор
            CheckedModelTwo("Тест на димо-оптични детектори"),
            CheckedModelTwo("Тест на термични детектори"),
            CheckedModelTwo("Тест на термодифиренциални детектори"),
            CheckedModelTwo("Тест на пламъчни детектори"),
            CheckedModelTwo("Тест на комбинирани детектори"),
            CheckedModelTwo("Тест на линейно-оптични детектори"),
            CheckedModelTwo("Тест на Взриво защитен димен детектор"),
            CheckedModelTwo("Тест на радиосигнала на безжични пожароизвестителни детектори")
        ),

        titles[14] to arrayListOf(
            // Проверка за наличие на свободно пространство от 0,5 м около всеки пожароизвестителен детектор

            FieldModel(""),
            CheckedModel(""),
        ),


        titles[15] to arrayListOf(
            // Тест на механизма на всеки един Ръчен пожароизвестителен бутон чрез тест ключ или премахване на чупещия се елемент

            CheckedModelTwo(""),
            CountModel(subTitle = "")
        ),
        titles[16] to arrayListOf(

            CheckedModelTwo(
                //  Проверка на безпрепятствен достъп до всички ръчни пожароизвестителни бутони
                ""
            ),
            CountModel("")
        ),

        titles[17] to arrayListOf(

            //Проверка за наличие на указателни знаци над ръчните пожароизвестителни бутони
            CheckedModelTwo(""),
            CountModel("")
        ),

        titles[18] to arrayListOf(
            // Вид на съд за гасителен агент
            //CheckedModel("Отворен"7),
            CheckedModel("Херметически затворен")
        ),

        titles[19] to arrayListOf(
            // Визуална проверка на съдовете за гасителен агент
            ExtendedCheckedModel(""),
            ExtendedCheckedModel("")
        ),

        titles[20] to arrayListOf(
            CheckedModel(
                //Проверка на система за откриване на течове
                ""
            )
        ),

        titles[21] to arrayListOf(
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

        titles[22] to arrayListOf(
            // Визуална проверка за повреди по маркучи и армировка
            CheckedModel("")
        ),

        titles[23] to arrayListOf(
            // Визуална проверка за увреждания (закрепване, корозия, механични наранявания и др.) по тръбопроводи
            CheckedModel(
                ""
            )
        ),
        titles[24] to arrayListOf(
            //  Визаулна проверка на дюзи за разпръскване на гасителен агент
            CheckedModel("")
        ),
        titles[25] to arrayListOf(
            // Продухване на тръбна мрежа на ПГИ с Азот против запушване
            CheckedModel("")
        ),

        titles[26] to arrayListOf(
            // Проверка за опасностите и непроницаемостта на затвореното пространство и промени, които биха могли да намалят ефективността на инсталацията
            CheckedModel("")
        ),
        titles[27] to arrayListOf(
            // Проверка за затваряне на Входна плъзгаща врата при сработване
            CheckedModel("")
        ),
        titles[28] to arrayListOf(
            // Проверка за сработване на сигнална лампа "СТОП ГАЗ"
            CheckedModel("")
        ),
        titles[29] to arrayListOf(
            CheckedModel(
                // Проверка за наличие на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение  обекта
                ""
            )
        ),
        titles[30] to arrayListOf(
            // Проверка за функциониране на ключ или мастър карта за достъп на пожарникарите до всяко едно помещение в обекта
            CheckedModel("")
        ),
        titles[31] to arrayListOf(
            CheckedModel(
                // Визуална проверка за структурни или екслоатационни промени в обекта, които биха попречили на работата на ръчните бутони
                ""
            )
        ),
        titles[32] to arrayListOf(
            // Проверка за наличие на актуалност на документацията, придружаваща ПИС
            CheckedModel("")
        ),
        titles[33] to arrayListOf(
            // Проверка за наличие и полълване на дневник на системата
            CheckedModel("")
        ),
        titles[34] to arrayListOf(
            // Забележка
            FieldModel(subTitle = "")
        )
)

val mapOfModels = mutableStateMapOf<String, ArrayList<IModel>>()


