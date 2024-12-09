class Toboggan 
    def initialize(locationMapString, x_per_move, y_per_move)
      raise unless locationMapString.is_a?(String)
      @x_position = 0
      @y_position = 0
      @x_per_move = x_per_move
      @y_per_move = y_per_move

      @locationMapMatrix = Array.new
      rowsString = locationMapString.split("\n")
      rowsString.each do |rowString|
        @locationMapMatrix.push rowString.chars 
      end 
    end

    def go_down_path_and_count_trees
        tree_counter = 0
        while get_current_square != nil 
            if move_and_return_square == "#"
             tree_counter = tree_counter + 1
            end
        end
        tree_counter
    end

    def move_and_return_square
        @x_position = @x_position + @x_per_move
        @y_position = @y_position + @y_per_move
        get_current_square
    end

    def get_current_square
        mapped_x_position = @x_position % @locationMapMatrix[0].length
        if @y_position < @locationMapMatrix.length
            @locationMapMatrix[@y_position][mapped_x_position]
        else
            nil
        end
    end
end