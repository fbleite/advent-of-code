require_relative "MultipleToboggans"
path = ARGF.read
puts path
path = path.gsub(/\r/, '')

puts MultipleToboggans.new.get_multiplied_path_trees(path)