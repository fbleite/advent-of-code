require_relative "BoardingPass"
require_relative "PlaneSeating"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
 
  def test_boarding_pass_1
    passString = 'BFFFBBFRRR'

    pass = BoardingPass.new(passString)
    assert_equal(70, pass.get_row_number)
    assert_equal(7, pass.get_column_number)
    assert_equal(567, pass.get_seat_id)
  end  
  
  def test_boarding_pass_2
    passString = 'BBFFBBFRLL'

    pass = BoardingPass.new(passString)
    assert_equal(102, pass.get_row_number)
    assert_equal(4, pass.get_column_number)
    assert_equal(820, pass.get_seat_id)
  end
  
  def test_boarding_pass_3
    passString = 'FFFBBBFRRR'

    pass = BoardingPass.new(passString)
    assert_equal(14, pass.get_row_number)
    assert_equal(7, pass.get_column_number)
    assert_equal(119, pass.get_seat_id)
  end  

  def test_plane_seating
    planeSeatingString = <<-EOF
BFFFBBFRRR
BBFFBBFRLL
FFFBBBFRRR
    EOF

    planeSeating = PlaneSeating.new(planeSeatingString)
    puts planeSeating.get_open_seats
    assert_equal(3, planeSeating.get_number_of_passes)

  end
end