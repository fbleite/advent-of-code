require_relative "BagTree"
class Bags
    def initialize(bagsString)
        raise unless bagsString.is_a?(String)
        bagsString.gsub!(/\r/, '')
        @bags = bagsString.split("\n").map()
    end

    def get_outer_bags_for(bag) 
        0
    end
end