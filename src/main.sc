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
                a: Перед вами сундук. Что будете делать?
                
                state: Open
                    q: * (~сломать|~открывать|~вскрывать) *
                    a: Вам выпали монеты, ровно {{ var coins = getRandomInt(11) }}. Преумножите или потратите?
                    
                    state: Choose
                        q: преумножить
                        a: {{ multiple(coins) }}
            
        state: NoMelon
            event: noMatch
            a: На эльфийском, друг!
        
    state: NoMatch
        event!: noMatch
        a: Попробуйте сказать это на эльфийском.