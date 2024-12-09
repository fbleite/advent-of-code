require_relative "PlaneSeating"
seats = ARGF.read
planeSeating = PlaneSeating.new(seats)
puts planeSeating.get_highest
