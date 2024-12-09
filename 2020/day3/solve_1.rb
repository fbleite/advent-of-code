require_relative "Toboggan"
path = ARGF.read
puts path
path = path.gsub(/\r/, '')
# puts passwords.length
toboggan = Toboggan.new(path, 3, 1)
puts toboggan.go_down_path_and_count_trees
