class BagTree
    def initialize(bagString)
        raise unless bagString.is_a?(String)
        @color = bagString.match(/(.+) bags contain/)[1]
        @childBags = bagString.scan(/([0-9]+) (.+?) bags?[\,|\.]/)
    end
    attr_reader :color, :childBags  
end
