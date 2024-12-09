require_relative "PlaneSeating"
seats = ARGF.read
planeSeating = PlaneSeating.new(seats)
my_seat = planeSeating.get_my_seat
puts my_seat
