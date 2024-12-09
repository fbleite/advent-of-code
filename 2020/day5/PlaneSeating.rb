require_relative "BoardingPass"
require 'set'
class PlaneSeating
    def initialize(planeSeatingString)
        raise unless planeSeatingString.is_a?(String)
        planeSeatingString.gsub!(/\r/, '')
        @boardingPasses = planeSeatingString.split("\n").map {
            |bpString|
            bp = BoardingPass.new(bpString)
            [bp.get_seat_id, bp]
        }.to_h
    end

    def get_number_of_passes
        @boardingPasses.length
    end

    def get_my_seat
        open_seats = get_open_seats
        open_seats_clone = get_open_seats.clone
        open_seats.select{|seat_id| 
            # puts seat_id 
            # puts open_seats_clone === seat_id+1
            is_present_next = open_seats_clone === seat_id + 1
            is_present_previous = open_seats_clone === seat_id - 1
            !is_present_next && !is_present_previous}
    end

    def get_open_seats
        id, bp = @boardingPasses.first
        # total_seats = (2 ** bp.get_pass_string.length)
        # total_seats = @boardingPasses.length + 1
        total_seats = get_highest
        total_seats.times.select { |seat_id| !@boardingPasses.has_key?(seat_id) }.to_set
    end
    def get_highest
        @boardingPasses.map{|id, bp| id}.max
    end
end


