require_relative "Toboggan"
require_relative "MultipleToboggans"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
 
  def test_individual_moves
    path = <<-EOF
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
EOF
    toboggan = Toboggan.new(path, 3, 1)    

    assert_equal(".", toboggan.get_current_square )
    assert_equal(".", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal(".", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal(".", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal("#", toboggan.move_and_return_square )
    assert_equal(nil, toboggan.move_and_return_square )
    assert_equal(nil, toboggan.move_and_return_square )
    assert_equal(nil, toboggan.move_and_return_square )
  end

  def test_simple_path
    path = <<-EOF
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
EOF
    toboggan = Toboggan.new(path, 3, 1)    

    assert_equal(7, toboggan.go_down_path_and_count_trees )
  end

  def test_multiple_toboggans
    path = <<-EOF
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
EOF
    

    assert_equal(336, MultipleToboggans.new.get_multiplied_path_trees(path))
  end
end