require_relative "CustomsDeclarations"
declarationsString = ARGF.read
declarations = CustomsDeclarations.new(declarationsString)
puts declarations.get_overlapping_yes_answers
