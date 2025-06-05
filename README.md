### Описание
Простой парсер LineByLine npcpos для over`подобных сборок l2 java серверов.\
Парсит npcpos от грации финал.

Закидывал весь скрипт без редактирования, отрабатывал без ошибок.\
Так же учтите, хеллбаунд остров содержит совершенно другую структуру данных, данный парсер не подойдет. 

Список групп добавленных да данный момент смотрите ниже.

##### Для сборки и запуска требуется Java 17+.

##### Создает xml по имени квадрата, а так же уникальные(осада, доминион, камалока и прочее).

###### Если решили отдельно вставлять квадрат
###### Начало: territory_begin
###### Конец: npcmaker_ex_end или npcmaker_end

##### Используется лицензия Apache License v2.0

##### Определяет день/ночь

##### Добавляет тег group для:
Осада замков
- gludio_castle_siege
- dion_castle_siege
- giran_castle_siege
- oren_castle_siege
- innadril_castle_siege
- aden_castle_siege
- goddard_castle_siege
- rune_castle_siege
- schuttgart_castle_siege

Группы захватываемых КХ (последующее ручное радактирование)
- devastated_castle_guards
- bandits_stronghold
- wild_beast_reserve
- fortress_of_dead_guards
- rainbow_springs

Группы катакомбы/некрополи(используются только dusk_spawn, dawn_spawn)
- dusk_spawn
- dawn_spawn
- competition(период соревнований, у оверов не используется), закомментирован в коде
- no_winner(без победителя, у оверов не используется), закомментирован в коде

Ивенты
- christmas
- event_gatekeeper

Остальное 
 - rune_castle_benom


