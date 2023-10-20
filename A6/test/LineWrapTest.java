import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Using sample paragraphs from
// https://patternbasedwriting.com/elementary_writing_success/paragraph-examples/

public class LineWrapTest {
    private String makeLine (int width) {
        char[] chars = new char[width];
        Arrays.fill(chars,'*');
        return new String(chars);
    }
    @Test
    public void myTest1 () {
        String s = "AAA BB CC DDDDD";
        LineWrap lw = new LineWrap(6);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        assertEquals("AAA BB\nCC\nDDDDD", s1);
        assertEquals("AAA\nBB CC\nDDDDD", s2);
    }
    @Test
    public void myTest2() {
        String s = "Cake Pie Ice cream";
        LineWrap lw = new LineWrap(6);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        assertEquals("Cake\nPie\nIce\ncream", s1);
        assertEquals("Cake\nPie\nIce\ncream", s2);
    }
    @Test
    public void myTest3() {
        String s = "AAAAA BBBBB CCCCCCCCCC DDDDDD EE";
        LineWrap lw = new LineWrap(11);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        assertEquals("AAAAA BBBBB\nCCCCCCCCCC\nDDDDDD EE", s1);
        assertEquals("AAAAA BBBBB\nCCCCCCCCCC\nDDDDDD\nEE", s2);

    }

    @Test
    public void test0 () {
        String s = "AAA BBBBB CC DDDDD";
        LineWrap lw = new LineWrap(6);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n", s1);
        System.out.printf("%s%n", s2);
    }

    @Test
    public void test1 () {
        String s = "AAA BB CC DDDDD";
        LineWrap lw = new LineWrap(6);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        assertEquals("AAA BB\nCC\nDDDDD", s1);
        assertEquals("AAA\nBB CC\nDDDDD", s2);
    }

    @Test
    public void test2 () {
        String s = "AA BB CC DD EEE F GGGGGGGGGGG GGGGGGGG HHHHHHH";
        LineWrap lw = new LineWrap(17);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n%n",makeLine(17),s1,s2);
/*
Expected output:

*****************
AA BB CC DD EEE F
GGGGGGGGGGG
GGGGGGGG HHHHHHH

AA BB CC DD EEE
F GGGGGGGGGGG
GGGGGGGG HHHHHHH

*/
    }

    @Test
    public void test3 () {
        String s = "On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space Center in Florida.";
        LineWrap lw = new LineWrap(31);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(31),s1,s2);
/*
Expected output:

*******************************
On July 16, 1969, the Apollo 11
spacecraft launched from the
Kennedy Space Center in
Florida.

On July 16, 1969, the Apollo
11 spacecraft launched from
the Kennedy Space Center in
Florida.

*/
    }

    @Test
    // this example from https://www.rosettacode.org/wiki/Word_wrap
    // designed to make the greedy algorithm look bad
    public void test4 () {
        String s = "In olden times when wishing still helped one, there lived a king whose daughters were all beautiful, but the youngest was so beautiful that the sun itself, which has seen so much, was astonished whenever it shone-in-her-face.  Close-by-the-king's castle lay a great dark forest, and under an old lime-tree in the forest was a well, and when the day was very warm, the king's child went out into the forest and sat down by the side of the cool-fountain, and when she was bored she took a golden ball, and threw it up on high and caught it, and this ball was her favorite plaything.";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n", makeLine(80), s1, s2);
/*
Expected output:

********************************************************************************
In olden times when wishing still helped one, there lived a king whose daughters
were all beautiful, but the youngest was so beautiful that the sun itself, which
has seen so much, was astonished whenever it shone-in-her-face.
Close-by-the-king's castle lay a great dark forest, and under an old lime-tree
in the forest was a well, and when the day was very warm, the king's child went
out into the forest and sat down by the side of the cool-fountain, and when she
was bored she took a golden ball, and threw it up on high and caught it, and
this ball was her favorite plaything.

In olden times when wishing still helped one, there lived a king whose
daughters were all beautiful, but the youngest was so beautiful that the sun
itself, which has seen so much, was astonished whenever it shone-in-her-face.
Close-by-the-king's castle lay a great dark forest, and under an old lime-tree
in the forest was a well, and when the day was very warm, the king's child went
out into the forest and sat down by the side of the cool-fountain, and when she
was bored she took a golden ball, and threw it up on high and caught it, and
this ball was her favorite plaything.


*/
    }

    @Test
    public void test5 () {
        String s = "On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space Center in Florida. Its mission was to go where no human being had gone before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic flood plain, on July 20, 1969. The moonwalk took place the following day. On July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from the Lunar Module and took his famous first step onto the moon’s surface. He declared, “That’s one small step for man, one giant leap for mankind.” It was a monumental moment in human history!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space
Center in Florida. Its mission was to go where no human being had gone
before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz
Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic
flood plain, on July 20, 1969. The moonwalk took place the following day. On
July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from the
Lunar Module and took his famous first step onto the moon’s surface. He
declared, “That’s one small step for man, one giant leap for mankind.” It was a
monumental moment in human history!

On July 16, 1969, the Apollo 11 spacecraft launched from the Kennedy Space
Center in Florida. Its mission was to go where no human being had gone
before—the moon! The crew consisted of Neil Armstrong, Michael Collins, and Buzz
Aldrin. The spacecraft landed on the moon in the Sea of Tranquility, a basaltic
flood plain, on July 20, 1969. The moonwalk took place the following day. On
July 21, 1969, at precisely 10:56 EDT, Commander Neil Armstrong emerged from
the Lunar Module and took his famous first step onto the moon’s surface. He
declared, “That’s one small step for man, one giant leap for mankind.” It was a
monumental moment in human history!


*/
    }

    @Test
    public void test6 () {
        String s = "It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he would become the first human being to ever walk on the moon. The journey had begun several days earlier, when on July 16th, the Apollo 11 launched from Earth headed into outer space. On board with Neil Armstrong were Michael Collins and Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before the actual walk. Upon Neil’s first step onto the moon’s surface, he declared, “That’s one small step for man, one giant leap for mankind.” It sure was!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he
would become the first human being to ever walk on the moon. The journey had
begun several days earlier, when on July 16th, the Apollo 11 launched from Earth
headed into outer space. On board with Neil Armstrong were Michael Collins and
Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before
the actual walk. Upon Neil’s first step onto the moon’s surface, he declared,
“That’s one small step for man, one giant leap for mankind.” It sure was!

It was July 21, 1969, and Neil Armstrong awoke with a start. It was the day he
would become the first human being to ever walk on the moon. The journey had
begun several days earlier, when on July 16th, the Apollo 11 launched from Earth
headed into outer space. On board with Neil Armstrong were Michael Collins and
Buzz Aldrin. The crew landed on the moon in the Sea of Tranquility a day before
the actual walk. Upon Neil’s first step onto the moon’s surface, he declared,
“That’s one small step for man, one giant leap for mankind.” It sure was!

*/
    }

    @Test
    public void test7 () {
        String s = "Here is the perfect system for cleaning your room. First, move all of the items that do not have a proper place to the center of the room. Get rid of at least five things that you have not used within the last year. Take out all of the trash, and place all of the dirty dishes in the kitchen sink. Now find a location for each of the items you had placed in the center of the room. For any remaining items, see if you can squeeze them in under your bed or stuff them into the back of your closet. See, that was easy!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
Here is the perfect system for cleaning your room. First, move all of the items
that do not have a proper place to the center of the room. Get rid of at least
five things that you have not used within the last year. Take out all of the
trash, and place all of the dirty dishes in the kitchen sink. Now find a
location for each of the items you had placed in the center of the room. For any
remaining items, see if you can squeeze them in under your bed or stuff them
into the back of your closet. See, that was easy!

Here is the perfect system for cleaning your room. First, move all of the
items that do not have a proper place to the center of the room. Get rid of at
least five things that you have not used within the last year. Take out all of
the trash, and place all of the dirty dishes in the kitchen sink. Now find a
location for each of the items you had placed in the center of the room. For
any remaining items, see if you can squeeze them in under your bed or stuff them
into the back of your closet. See, that was easy!


*/
    }

    @Test
    public void test8 () {
        String s = "Oceans and lakes have much in common, but they are also quite different. Both are bodies of water, but oceans are very large bodies of salt water, while lakes are much smaller bodies of fresh water. Lakes are usually surrounded by land, while oceans are what surround continents. Both have plants and animals living in them. The ocean is home to the largest animals on the planet, whereas lakes support much smaller forms of life. When it is time for a vacation, both will make a great place to visit and enjoy.";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
Oceans and lakes have much in common, but they are also quite different. Both
are bodies of water, but oceans are very large bodies of salt water, while lakes
are much smaller bodies of fresh water. Lakes are usually surrounded by land,
while oceans are what surround continents. Both have plants and animals living
in them. The ocean is home to the largest animals on the planet, whereas lakes
support much smaller forms of life. When it is time for a vacation, both will
make a great place to visit and enjoy.

Oceans and lakes have much in common, but they are also quite different. Both
are bodies of water, but oceans are very large bodies of salt water, while lakes
are much smaller bodies of fresh water. Lakes are usually surrounded by land,
while oceans are what surround continents. Both have plants and animals living
in them. The ocean is home to the largest animals on the planet, whereas lakes
support much smaller forms of life. When it is time for a vacation, both will
make a great place to visit and enjoy.


*/
    }

    @Test
    public void test9 () {
        String s = "The Blue Whales just played their first baseball game of the new season; I believe there is much to be excited about. Although they lost, it was against an excellent team that had won the championship last year. The Blue Whales fell behind early but showed excellent teamwork and came back to tie the game. The team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5 fielding errors, which kept the other team in the lead the entire game. The game ended with the umpire making a bad call, and if the call had gone the other way, the Blue Whales might have actually won the game. It wasn’t a victory, but I say the Blue Whales look like they have a shot at the championship, especially if they continue to improve.";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
The Blue Whales just played their first baseball game of the new season; I
believe there is much to be excited about. Although they lost, it was against an
excellent team that had won the championship last year. The Blue Whales fell
behind early but showed excellent teamwork and came back to tie the game. The
team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5
fielding errors, which kept the other team in the lead the entire game. The game
ended with the umpire making a bad call, and if the call had gone the other way,
the Blue Whales might have actually won the game. It wasn’t a victory, but I say
the Blue Whales look like they have a shot at the championship, especially if
they continue to improve.

The Blue Whales just played their first baseball game of the new season; I
believe there is much to be excited about. Although they lost, it was against
an excellent team that had won the championship last year. The Blue Whales fell
behind early but showed excellent teamwork and came back to tie the game. The
team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5
fielding errors, which kept the other team in the lead the entire game. The game
ended with the umpire making a bad call, and if the call had gone the other way,
the Blue Whales might have actually won the game. It wasn’t a victory, but I say
the Blue Whales look like they have a shot at the championship, especially if
they continue to improve.


*/
    }

    @Test
    public void test10 () {
        String s = "The school fair is right around the corner, and tickets have just gone on sale. We are selling a limited number of tickets at a discount, so move fast and get yours while they are still available. This is going to be an event you will not want to miss! First off, the school fair is a great value when compared with other forms of entertainment. Also, your ticket purchase will help our school, and when you help the school, it helps the entire community. But that’s not all! Every ticket you purchase enters you in a drawing to win fabulous prizes. And don’t forget, you will have mountains of fun because there are acres and acres of great rides, fun games, and entertaining attractions! Spend time with your family and friends at our school fair. Buy your tickets now!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
The school fair is right around the corner, and tickets have just gone on sale.
We are selling a limited number of tickets at a discount, so move fast and get
yours while they are still available. This is going to be an event you will not
want to miss! First off, the school fair is a great value when compared with
other forms of entertainment. Also, your ticket purchase will help our school,
and when you help the school, it helps the entire community. But that’s not all!
Every ticket you purchase enters you in a drawing to win fabulous prizes. And
don’t forget, you will have mountains of fun because there are acres and acres
of great rides, fun games, and entertaining attractions! Spend time with your
family and friends at our school fair. Buy your tickets now!

The school fair is right around the corner, and tickets have just gone on sale.
We are selling a limited number of tickets at a discount, so move fast and get
yours while they are still available. This is going to be an event you will not
want to miss! First off, the school fair is a great value when compared with
other forms of entertainment. Also, your ticket purchase will help our school,
and when you help the school, it helps the entire community. But that’s not all!
Every ticket you purchase enters you in a drawing to win fabulous prizes. And
don’t forget, you will have mountains of fun because there are acres and acres
of great rides, fun games, and entertaining attractions! Spend time with your
family and friends at our school fair. Buy your tickets now!


*/
    }

    @Test
    public void test11 () {
        String s = "The school fair is right around the corner, and tickets have just gone on sale. Even though you may be busy, you will still want to reserve just one day out of an entire year to relax and have fun with us. Even if you don’t have much money, you don’t have to worry. A school fair is a community event, and therefore prices are kept low. Perhaps, you are still not convinced. Maybe you feel you are too old for fairs, or you just don’t like them. Well, that’s what my grandfather thought, but he came to last year’s school fair and had this to say about it: “I had the best time of my life!” While it’s true that you may be able to think of a reason not to come, I’m also sure that you can think of several reasons why you must come.  We look forward to seeing you at the school fair!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
The school fair is right around the corner, and tickets have just gone on sale.
Even though you may be busy, you will still want to reserve just one day out of
an entire year to relax and have fun with us. Even if you don’t have much money,
you don’t have to worry. A school fair is a community event, and therefore
prices are kept low. Perhaps, you are still not convinced. Maybe you feel you
are too old for fairs, or you just don’t like them. Well, that’s what my
grandfather thought, but he came to last year’s school fair and had this to say
about it: “I had the best time of my life!” While it’s true that you may be able
to think of a reason not to come, I’m also sure that you can think of several
reasons why you must come.  We look forward to seeing you at the school fair!

The school fair is right around the corner, and tickets have just gone on sale.
Even though you may be busy, you will still want to reserve just one day out of
an entire year to relax and have fun with us. Even if you don’t have much money,
you don’t have to worry. A school fair is a community event, and therefore
prices are kept low. Perhaps, you are still not convinced. Maybe you feel
you are too old for fairs, or you just don’t like them. Well, that’s what my
grandfather thought, but he came to last year’s school fair and had this to say
about it: “I had the best time of my life!” While it’s true that you may be able
to think of a reason not to come, I’m also sure that you can think of several
reasons why you must come.  We look forward to seeing you at the school fair!


*/
    }

    @Test
    public void test12 () {
        String s = "Last week we installed a kitty door so that our cat could come and go as she pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the kitty door. We tried pushing her through, and that caused her to be even more afraid. The kitty door was dark, and she couldn’t see what was on the other side. The first step we took in solving this problem was taping the kitty door open. After a couple of days, she was confidently coming and going through the open door. However, when we removed the tape and closed the door, once again, she would not go through. They say you catch more bees with honey, so we decided to use food as bait. We would sit next to the kitty door with a can of wet food and click the top of the can. When kitty came through the closed door, we would open the can and feed her. It took five days of doing this to make her unafraid of using the kitty door. Now we have just one last problem: our kitty controls our lives!";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
Last week we installed a kitty door so that our cat could come and go as she
pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the
kitty door. We tried pushing her through, and that caused her to be even more
afraid. The kitty door was dark, and she couldn’t see what was on the other
side. The first step we took in solving this problem was taping the kitty door
open. After a couple of days, she was confidently coming and going through the
open door. However, when we removed the tape and closed the door, once again,
she would not go through. They say you catch more bees with honey, so we decided
to use food as bait. We would sit next to the kitty door with a can of wet food
and click the top of the can. When kitty came through the closed door, we would
open the can and feed her. It took five days of doing this to make her unafraid
of using the kitty door. Now we have just one last problem: our kitty controls
our lives!

Last week we installed a kitty door so that our cat could come and go as she
pleases. Unfortunately, we ran into a problem. Our cat was afraid to use the
kitty door. We tried pushing her through, and that caused her to be even more
afraid. The kitty door was dark, and she couldn’t see what was on the other
side. The first step we took in solving this problem was taping the kitty door
open. After a couple of days, she was confidently coming and going through the
open door. However, when we removed the tape and closed the door, once again,
she would not go through. They say you catch more bees with honey, so we decided
to use food as bait. We would sit next to the kitty door with a can of wet food
and click the top of the can. When kitty came through the closed door, we would
open the can and feed her. It took five days of doing this to make her unafraid
of using the kitty door. Now we have just one last problem: our kitty controls
our lives!


*/
    }


    @Test
    public void test13 () {
        String s = "Sunset is the time of day when our sky meets the outer space solar winds. There are blue, pink, and purple swirls, spinning and twisting, like clouds of balloons caught in a whirlwind. The sun moves slowly to hide behind the line of horizon, while the moon races to take its place in prominence atop the night sky. People slow to a crawl, entranced, fully forgetting the deeds that must still be done. There is a coolness, a calmness, when the sun does set.";
        LineWrap lw = new LineWrap(80);
        String s1 = lw.runGreedy(s);
        String s2 = lw.runTopdown(s);
        System.out.printf("%s%n%s%n%n%s%n%n",makeLine(80),s1,s2);
/*
Expected output:

********************************************************************************
Sunset is the time of day when our sky meets the outer space solar winds. There
are blue, pink, and purple swirls, spinning and twisting, like clouds of
balloons caught in a whirlwind. The sun moves slowly to hide behind the line of
horizon, while the moon races to take its place in prominence atop the night
sky. People slow to a crawl, entranced, fully forgetting the deeds that must
still be done. There is a coolness, a calmness, when the sun does set.

Sunset is the time of day when our sky meets the outer space solar winds.
There are blue, pink, and purple swirls, spinning and twisting, like clouds of
balloons caught in a whirlwind. The sun moves slowly to hide behind the line
of horizon, while the moon races to take its place in prominence atop the night
sky. People slow to a crawl, entranced, fully forgetting the deeds that must
still be done. There is a coolness, a calmness, when the sun does set.


*/
    }


}
