require_relative "Toboggan"
class MultipleToboggans
    def get_multiplied_path_trees(path)
        toboggan1 = Toboggan.new(path, 1, 1)
        toboggan2 = Toboggan.new(path, 3, 1)
        toboggan3 = Toboggan.new(path, 5, 1)
        toboggan4 = Toboggan.new(path, 7, 1)
        toboggan5 = Toboggan.new(path, 1, 2)
        toboggan1.go_down_path_and_count_trees *
         toboggan2.go_down_path_and_count_trees *
          toboggan3.go_down_path_and_count_trees * 
          toboggan4.go_down_path_and_count_trees *
           toboggan5.go_down_path_and_count_trees
    end
end