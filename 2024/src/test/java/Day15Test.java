import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void parseInput() {
        var input = """
                            ##########
                            #..O..O.O#
                            #......O.#
                            #.OO..O.O#
                            #..O@..O.#
                            #O#..O...#
                            #O..O..O.#
                            #.OO.O.OO#
                            #....O...#
                            ##########
                
                            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
                            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
                            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
                            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
                            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
                            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
                            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
                            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
                            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
                            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
                """;
        var map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
                {'#', '.', '.', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', '.', '.', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', 'O', '@', '.', '.', 'O', '.', '#',},
                {'#', 'O', '#', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', 'O', '.', '.', 'O', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', 'O', '.', 'O', 'O', '#',},
                {'#', '.', '.', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',}};
        var directions = "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^";
        var position = new Coordinate(4, 4);
        var parsedInput = Day15.parseInput(input);
        assertArrayEquals(map, parsedInput.map());
        assertEquals(directions, parsedInput.directions());
        assertEquals(directions, parsedInput.directions());
        assertEquals(position, parsedInput.position());
    }

    @Test
    void moveDirection_free() {
        var input = """
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#..O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                
                v
                """;
        var map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
                {'#', '.', '.', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', '.', '.', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', 'O', '.', '.', '.', 'O', '.', '#',},
                {'#', 'O', '#', '.', '@', 'O', '.', '.', '.', '#',},
                {'#', 'O', '.', '.', 'O', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', 'O', '.', 'O', 'O', '#',},
                {'#', '.', '.', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',}};
        var position = new Coordinate(5, 4);
        var parsedInput = Day15.parseInput(input);
        var md = Day15.moveDirection(parsedInput);
        assertArrayEquals(map, md.map());
        assertEquals(position, md.position());
    }

    @Test
    void moveDirection_wall() {
        var input = """
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#.#O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                
                v
                """;
        var map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
                {'#', '.', '.', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', '.', '.', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', 'O', '@', '.', '.', 'O', '.', '#',},
                {'#', 'O', '#', '.', '#', 'O', '.', '.', '.', '#',},
                {'#', 'O', '.', '.', 'O', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', 'O', '.', 'O', 'O', '#',},
                {'#', '.', '.', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',}};
        var position = new Coordinate(4, 4);
        var parsedInput = Day15.parseInput(input);
        var md = Day15.moveDirection(parsedInput);
        assertArrayEquals(map, md.map());
        assertEquals(position, md.position());
    }

    @Test
    void moveDirection_box() {
        var input = """
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#..O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                
                <
                """;
        var map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
                {'#', '.', '.', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', '.', '.', '.', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', '.', 'O', '.', 'O', '#',},
                {'#', '.', 'O', '@', '.', '.', '.', 'O', '.', '#',},
                {'#', 'O', '#', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', 'O', '.', '.', 'O', '.', '.', 'O', '.', '#',},
                {'#', '.', 'O', 'O', '.', 'O', '.', 'O', 'O', '#',},
                {'#', '.', '.', '.', '.', 'O', '.', '.', '.', '#',},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',}};
        var position = new Coordinate(4, 3);
        var parsedInput = Day15.parseInput(input);
        var md = Day15.moveDirection(parsedInput);
        assertArrayEquals(map, md.map());
        assertEquals(position, md.position());
    }


    @Test
    void moveAll() {
        var input = """
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                
                <^^>>>vv<v>>v<<
                """;
        var map = new char[][]{
                "########".toCharArray(),
                "#....OO#".toCharArray(),
                "##.....#".toCharArray(),
                "#.....O#".toCharArray(),
                "#.#O@..#".toCharArray(),
                "#...O..#".toCharArray(),
                "#...O..#".toCharArray(),
                "########".toCharArray()
        };
        var position = new Coordinate(4, 4);
        var parsedInput = Day15.parseInput(input);
        var md = Day15.moveAll(parsedInput);
        assertArrayEquals(map, md.map());
        assertEquals(position, md.position());
        assertTrue(md.directions().isBlank());
        assertEquals(2028, Day15.gpsAllBoxes(md.map()));
    }



    @Test
    void moveAll_large() {
        var input = """
##########
#..O..O.O#
#......O.#
#.OO..O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
                """;
        var map = new char[][]{
                "##########".toCharArray(),
                "#.O.O.OOO#".toCharArray(),
                "#........#".toCharArray(),
                "#OO......#".toCharArray(),
                "#OO@.....#".toCharArray(),
                "#O#.....O#".toCharArray(),
                "#O.....OO#".toCharArray(),
                "#O.....OO#".toCharArray(),
                "#OO....OO#".toCharArray(),
                "##########".toCharArray()
        };
        var position = new Coordinate(4, 3);
        var parsedInput = Day15.parseInput(input);
        var md = Day15.moveAll(parsedInput);
        assertArrayEquals(map, md.map());
        assertEquals(position, md.position());
        assertTrue(md.directions().isBlank());
        assertEquals(10092, Day15.gpsAllBoxes(md.map()));
    }

    @Test
    void resize() {
        var input = """
##########
#..O..O.O#
#......O.#
#.OO..O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

<vv>
                """;
        var map = new char[][]{
                "####################".toCharArray(),
                "##....[]....[]..[]##".toCharArray(),
                "##............[]..##".toCharArray(),
                "##..[][]....[]..[]##".toCharArray(),
                "##....[]@.....[]..##".toCharArray(),
                "##[]##....[]......##".toCharArray(),
                "##[]....[]....[]..##".toCharArray(),
                "##..[][]..[]..[][]##".toCharArray(),
                "##........[]......##".toCharArray(),
                "####################".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        assertArrayEquals(map, Day15.resizeMap(parsedInput.map()));
    }

    @Test
    void resize_box_lateral() {
        var input = """
##########
#..O..O.O#
#......O.#
#.OO..O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

<<<<<v
                """;
        var map = new char[][]{
                "####################".toCharArray(),
                "##....[]....[]..[]##".toCharArray(),
                "##............[]..##".toCharArray(),
                "##..[][]....[]..[]##".toCharArray(),
                "##[]@.........[]..##".toCharArray(),
                "##[]##....[]......##".toCharArray(),
                "##[]....[]....[]..##".toCharArray(),
                "##..[][]..[]..[][]##".toCharArray(),
                "##........[]......##".toCharArray(),
                "####################".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }


    @Test
    void resize_box_vertical() {
        var input = """
##########
#..O..O.O#
#......O.#
#.OOO.O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

^
                """;
        var map = new char[][]{
                "####################".toCharArray(),
                "##....[]....[]..[]##".toCharArray(),
                "##......[]....[]..##".toCharArray(),
                "##..[][]@...[]..[]##".toCharArray(),
                "##....[]......[]..##".toCharArray(),
                "##[]##....[]......##".toCharArray(),
                "##[]....[]....[]..##".toCharArray(),
                "##..[][]..[]..[][]##".toCharArray(),
                "##........[]......##".toCharArray(),
                "####################".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }


    @Test
    void resize_box_example() {
        var input = """
#######
#...#.#
#.....#
#..OO@#
#..O..#
#.....#
#######

<vv<<^^<<^^
                """;
        var map = new char[][]{
                "##############".toCharArray(),
                "##...[].##..##".toCharArray(),
                "##...@.[]...##".toCharArray(),
                "##....[]....##".toCharArray(),
                "##..........##".toCharArray(),
                "##..........##".toCharArray(),
                "##############".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }

    @Test
    void resize_box_example_large() {

        var input = """
##########
#..O..O.O#
#......O.#
#.OO..O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
                """;
        var map = new char[][]{
                "####################".toCharArray(),
                "##[].......[].[][]##".toCharArray(),
                "##[]...........[].##".toCharArray(),
                "##[]........[][][]##".toCharArray(),
                "##[]......[]....[]##".toCharArray(),
                "##..##......[]....##".toCharArray(),
                "##..[]............##".toCharArray(),
                "##..@......[].[][]##".toCharArray(),
                "##......[][]..[]..##".toCharArray(),
                "####################".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
        assertEquals(9021, Day15.gpsAllBoxes(actual.map()));
    }

    @Test
    void resize_box_vertical_bug() {
        var input = """
#####
#...#
#.O.#
#.O@#
#...#
#####

<v<^
                """;
        var map = new char[][]{
                "##########".toCharArray(),
                "##..[]..##".toCharArray(),
                "##.[]...##".toCharArray(),
                "##..@...##".toCharArray(),
                "##......##".toCharArray(),
                "##########".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }

    @Test
    void resize_box_vertical_bug_like_really() {
        var input = """
#####
#...#
#.O.#
#.O.#
#.@.#
#####

^
                """;
        var map = new char[][]{
                "##########".toCharArray(),
                "##..[]..##".toCharArray(),
                "##..[]..##".toCharArray(),
                "##..@...##".toCharArray(),
                "##......##".toCharArray(),
                "##########".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }


    @Test
    void resize_box_vertical_bug_come_on() {
        var input = """
#####
#...#
#.O.#
#.O@#
#...#
#####

<^
                """;
        var map = new char[][]{
                "##########".toCharArray(),
                "##..[]..##".toCharArray(),
                "##...@..##".toCharArray(),
                "##.[]...##".toCharArray(),
                "##......##".toCharArray(),
                "##########".toCharArray(),
        };
        var parsedInput = Day15.parseInput(input);
        var resizedMap = Day15.resizeMap(parsedInput.map());
        var md = new Day15.MapAndDirections(resizedMap, parsedInput.directions(), Day15.findRobot(resizedMap));
        var actual = Day15.moveAll(md);
        assertArrayEquals(map, actual.map());
    }



//
//    @Test
//    void moveAll_large_gps() {
//        var input = """
//##########
//#..O..O.O#
//#......O.#
//#.OO..O.O#
//#..O@..O.#
//#O#..O...#
//#O..O..O.#
//#.OO.O.OO#
//#....O...#
//##########
//
//<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
//vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
//><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
//<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
//^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
//^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
//>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
//<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
//^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
//v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
//                """;
//
//        var position = new Coordinate(4, 3);
//        var parsedInput = Day15.parseInput(input);
//
//        var resizedMap = Day15.resizeMap(parsedInput.map());
//        var md = new Day15.MapAndDirections(resizedMap, md.directions(), Day15.findRobot(resizedMap));
//
//        md = Day15.moveAll(parsedInput);
//        assertEquals(10092, Day15.gpsAllBoxes(md.map()));
//    }

}