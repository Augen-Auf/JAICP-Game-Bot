require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: functions.js

theme: /

    state: Start
        q!: $regex</start>
        a: Молви друг и войди!
        
        state: Melon
            q: мелон
            a: Перед тобой три коридора. В какой пойдешь?
            
            state: Left
                q: * *лев* *
                a: Тебя съел дракон, Gameover! Сыграете еще раз?
                
                state: Restart
                    q: да
                    go!: /Start
                
            state: Right
                q: * *прав* *
                a: Ты упал в пропасть, Gameover!
                    
            state: Straight
                q: * *прям* *
                a: Перед тобой сундук. Что будешь делать?
                
                state: Open
                    q: открыть
                    script: 
                        $session.coins = getRandomInt(11);
                        $reactions.answer("Вау! Тебе выпали монеты, ровно " + $session.coins)
                    a: Хочешь преумножить или потратить куш?
                    
                    state: ChooseMultiple 
                        q: преумножить
                        a: Поздравляю! Твой выигрыш составил: {{ multipleCoins($session.coins) }} монет!
                        
                    state: ChooseLost 
                        q: потратить
                        a: Хаха! Ты потерял все монеты, теперь у тебя их  {{ subtractCoins() }}, Gameover!
            
        state: NoMelon
            event: noMatch
            a: На эльфийском, друг!
        
    state: NoMatch
        event!: noMatch
        a: Попробуй сказать это на эльфийском.