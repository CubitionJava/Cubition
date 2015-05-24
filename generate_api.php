<?php

// ==== HOW TO RUN ====
// 1. Start your terminal/console
// 2. cd to this directory
// 3. php -f generate_api.php

$inputFile = 'New API design.jsd';
$outDir = 'newapi/src/main/java';

// Do not change anything below this line
// --------------------------------------

header ('Content-type: text');

$start = microtime ();

# Function to display errors
function exc($t,$l='') {global $lines;exit ('Compilation error: '. $t .($l!=''?' (on line '.$l.')'.PHP_EOL.PHP_EOL.$lines[$l-1]:'')); }
# Grab schema
$input = file_get_contents ($inputFile);
$lines = StringUtils::splitByLine ($input);

# Define some variables
$scope = '';
$ns = '';
$qjs = array ();
$curr = null;

# Go over each line
foreach ($lines as $lnum => $line) {
	# Remove spacing
	$line = trim ($line);
	$lnum += 1;

	# No empty lines or comments
	if (strlen ($line) > 3 && $line [0] != '#') {
		# Static modifier
		$static = $line [0] == '$';
		if ($static)
			$line = substr ($line, 1);
		
		# Definition
		$d = trim (substr ($line, 1));

		# Namespace (package)
		if ($line [0] == '/') {
			$ns = str_replace (array ('/', '\\'), '.', substr ($line, 1));
		}
		
		# Class
		elseif ($line [0] == 'C') {
			pointermove ();
			
			# Define new class
			$scope = 'class';
			$curr = new QJClass;
			$curr->name = trim (StringUtils::untilFirst ('=', StringUtils::untilFirst (':', $d)));
			
			if (is_numeric($curr->name [0]))
				exc ('Cannot start a class name with a number', $lnum);
			
			$curr->extends = trim (StringUtils::untilFirst (':', StringUtils::fromFirst ('=', $d)));
			$curr->implements = cln (StringUtils::fromFirst (':', $d));
			$curr->ns = $ns;
		}
		
		# Interface
		elseif ($line [0] == 'I') {
			pointermove ();
			
			# Define new interface
			$scope = 'interface';
			$curr = new QJInterface;
			$curr->name = trim(StringUtils::untilFirst (':', $d));
			
			if (is_numeric($curr->name [0]))
				exc ('Cannot start a interface name with a number', $lnum);
			
			$curr->extends += cln(trim(StringUtils::fromFirst (':', $d)));
			$curr->ns = $ns;
		}
		
		# Enum
		elseif ($line [0] == 'E') {
			pointermove ();
			
			$scope = 'enum';
			$curr = new QJEnum;
			$curr->name = trim($d);
			
			if (is_numeric($curr->name [0]))
				exc ('Cannot start an enum name with a number', $lnum);
			
			$curr->ns = $ns;
		}
		
		# Methods / functions
		elseif ($line [0] == 'F') {
			# Must be inside a class, interface or enum
			if ($scope == 'class' || $scope == 'interface' || $scope == 'enum')
			{
				# Define new method
				$m = new QJMethod;
				$m->name = trim(StringUtils::untilFirst ('(', $d));
				
				if (is_numeric($m->name [0]))
					exc ('Cannot start a function name with a number', $lnum);
				
				$m->args ($d);
				$m->return = trim(StringUtils::fromLast (':', $d));
				$len = strlen($m->return);
				if ($len > 4)
					if ($m->return [0] == '{' && $m->return [$len - 1] == '}')
						$m->return = 'HashMap<'.substr($m->return, 1, -1).'>';
				$curr->imports ['java.util.HashMap'] = null;
				
				$m->static = $static;
				$m->master = $curr->name;
				
				# Add method to the current thing
				$curr->methods [] = $m;
			}
			else
				exc('Methods can only be in classes or interfaces', $lnum); 
		}
		
		# Property
		elseif ($line [0] == 'P') {
			# Must be inside a class or enum
			if ($scope == 'class' || $scope == 'enum')
			{
				$type = trim (StringUtils::fromFirst (':', $d));
				$len = strlen($type);
				
				# {Hash, Map}-parsing
				if ($len > 4)
					if ($type [0] == '{' && $type [$len - 1] == '}')
						$type = 'HashMap<'.substr($type, 1, -1).'>';
				$curr->imports ['java.util.HashMap'] = null;
				
				$name = trim(StringUtils::untilFirst (':', $d));
				
				if (is_numeric($name [0]))
					exc ('Cannot start a property name with a number', $lnum);
				
				$curr->properties [$name] = $type;
			}
			else
				exc ('Cannot define a property outside a class', $lnum);
		}
		
		# Enum constant
		elseif ($line [0] == '-')
		{
			# Must be inside an enum
			if ($scope == 'enum')
				$curr->defs [] = trim ($d);
			else
				exc ('Not in enum', $lnum);
		}
		# Not recognized?
		else
			exc('Token "'. $line [0] .'" not recognized', $lnum);
	}
}

pointermove ();

# Debug log
foreach ($qjs as $qj)
{
	# Get the path for this class/interface/enum/whevver
	# and make the directory if it does not exist yet.
	$ddir = $outDir .DIRECTORY_SEPARATOR. str_replace('.', DIRECTORY_SEPARATOR, $qj->ns);
	if (!is_dir ($ddir))
		mkdir ($ddir, 0777, true);
	
	# Save the generated Java code
	if (file_put_contents ($ddir . DIRECTORY_SEPARATOR . $qj->name .'.java', (string) $qj))
		echo 'Created file: '. $qj->name .'.java' . PHP_EOL;
	else
		echo 'Could not create file: '. $qj->name .'.java' . PHP_EOL;
}
echo  PHP_EOL . 'Finished in '. (microtime () - $start) .' seconds';

// Language elements: change output here
// -------------------------------------

class QJInterface {
	public $name;
	public $methods = array ();
	public $ns = '';
	public $extends = array ();
	public $imports = array ();

	public function __toString () {
		$out = '';
		if ($this->ns != '')
			$out .= 'package '. $this->ns .';'.PHP_EOL.PHP_EOL;
		
		foreach ($this->imports as $import => $n)
			$out .= 'import '.$import.';' . PHP_EOL;
		
		
		$out .= 'interface '. $this->name;
		$out .=
			count($this->extends) > 0
			?' extends '. implode (', ', $this->extends)
			:''
			;
		$out .= ' {'.PHP_EOL;
		foreach ($this->methods as $method)
			$out .= "\t". ((string) $method) . ';'. PHP_EOL;
		$out .= '}' . PHP_EOL;
		return $out;
	}
}

class QJClass {
	public $name;
	public $methods = array ();
	public $ns = '';
	public $extends = '';
	public $properties = array ();
	public $implements = array ();
	public $imports = array ();

	public function __toString () {
		$out = '';
		if ($this->ns != '')
			$out .= 'package '. $this->ns .';'.PHP_EOL.PHP_EOL;
		
		foreach ($this->imports as $import => $n)
			$out .= 'import '.$import.';' . PHP_EOL;
		
		$out .= 'class '. $this->name;
		$out .= ($this->extends != ''
				? ' extends '. $this->extends
				: ''
				);
		$out .= (count ($this->implements) > 0
				? ' implements '. implode (', ', $this->implements)
				: ''
				);
		$out .= ' {'. PHP_EOL;
		foreach ($this->properties as $prop => $type)
			$out .= "\tpublic ". $type .' '. $prop . ';'. PHP_EOL;
		
		foreach ($this->methods as $method)
			$out .= "\t". ((string) $method) . ' {'. PHP_EOL . "\t\t// TODO: Add method body" . PHP_EOL . ($method->name != '@' && $method->return != 'void' ? "\t\treturn null;".PHP_EOL:'')."\t}" . PHP_EOL;
		
		$out .= '}' . PHP_EOL;
		return $out;
	}
}

class QJEnum extends QJCLass {
	
	public $defs = array ();
	
	public function __toString () {
		$out = '';
		if ($this->ns != '')
			$out .= 'package '. $this->ns .';'.PHP_EOL.PHP_EOL;
		
		foreach ($this->imports as $import => $n)
			$out .= 'import '.$import.';' . PHP_EOL;
		
		$out .= 'enum '. $this->name .' {'.PHP_EOL;
		
		# Enum constants
		foreach ($this->defs as $dn => $def)
			$out .= ($dn != 0 ? ','. PHP_EOL : '') . "\t" . trim ($def);
		$out .= ';'. PHP_EOL;
		
		# Properties
		foreach ($this->properties as $prop => $type)
			$out .= "\tpublic ". $type .' '. $prop . ';'. PHP_EOL;
		
		# Fix method visibility
		foreach ($this->methods as $method)
			if ($method->name == '@' && $method->visibility == 'public')
				$method->visibility = '';
		
		# Methods
		foreach ($this->methods as $method)
			$out .= "\t". ((string) $method) . ' {'. PHP_EOL . "\t\t// TODO: Add method body" . PHP_EOL . ($method->name != '@' && $method->return != 'void' ? "\t\treturn null;".PHP_EOL:'')."\t}" . PHP_EOL;
		
		$out .= '}' . PHP_EOL;
		return $out;
	}
}

class QJMethod {
	public $name;
	public $arguments = array ();
	public $return = 'void';
	public $static = false;
	public $visibility = 'public';
	public $master;

	public function args ($in) {
		$mid = StringUtils::mid('(', ')', $in);
		$args = explode (', ', $mid);
		foreach ($args as $arg) {
			$this->arguments [StringUtils::fromFirst (' ', $arg)] = StringUtils::untilFirst (' ', $arg);
		}
	}
	
	public function __toString () {
		$out  = ($this->visibility != '' ? $this->visibility .' ' : '');
		$out .= ($this->name == '@'
					? $this->master
					: ($this->static ? 'static ' : '') . $this->return .' '. $this->name);
		$out .= ' (';
		$argout = '';
		foreach ($this->arguments as $arg => $type)
			$argout .= ($argout == '' ? '' : ', ') . $type .' '. $arg;
		$out .= $argout .')';
		return $out;
	}
}

function cln ($in) {
	$ps = explode (',', $in);
	foreach ($ps as $k => $v) $ps [$k] = trim ($v);
	foreach ($ps as $k => $v) if ($v == '') unset ($ps [$k]);
	return $ps;
}

function pointermove () {
	global $scope, $qjs, $curr;
	
	if ($scope != '')
		$qjs [$curr->name] = $curr;
}


class StringUtils {

	/**
	 * Constructor Lock
	 */
	private function __construct () {}

	const LINEBREAK_CRLF = "\r\n";
	const LINEBREAK_CR = "\r";
	const LINEBREAK_LF = "\n";
	protected $MB_ENABLED = null;

	function startsWith($haystack, $needle) {
		return $needle === "" || strrpos($haystack, $needle, -strlen($haystack)) !== FALSE;
	}
	function endsWith($haystack, $needle) {
		return $needle === "" || (($temp = strlen($haystack) - strlen($needle)) >= 0 && strpos($haystack, $needle, $temp) !== FALSE);
	}

	public static function explodeMultiple (array $delimiters, $input, $limit = null) {
		$delimiterCount = count ($delimiters);

		if ($delimiterCount == 0)
			return $input;

		if ($delimiterCount == 1)
			return explode ($delimiters [0], $input);

		$firstDelimiter = array_shift ($delimiters);

		foreach ($delimiters as $delimiter)
				$input = str_replace ($delimiter, $firstDelimiter, $input);

		return (is_int ($limit) ? explode ($firstDelimiter, $input, $limit) : explode ($firstDelimiter, $input));
	}

	public static function uniformLineBreak ($input, $break = PHP_EOL)
	{
		if ($break != self::LINEBREAK_CRLF && $break != self::LINEBREAK_CR && $break != self::LINEBREAK_LF)
		{
			trigger_error ('[Seclude] StringUtils::'. __FUNCTION__ .'	Linebreak mode is not accepted. Using PHP_EOL default instead.', E_USER_WARNING);
			$break = PHP_EOL;
		}

		return preg_replace('~\R~u', $break, $input);
	}

	public static function splitByLine ($input, $mode = PHP_EOL)
	{
		return explode (PHP_EOL, static::uniformLineBreak ($input, $mode));
	}
	
	public static function multilen () {
		if (self::$MB_ENABLED == null) $MB_ENABLED = function_exists ('mb_strlen');
		
		$out = 0;
		if (self::$MB_ENABLED)
			foreach (func_get_args() as $arg) $out += mb_strlen($arg);
		else
			foreach (func_get_args() as $arg) $out += strlen($arg);
		return $out;
	}

	public static function mid ($s, $e, $str) {
		$p1 = strpos ($str, $s);
		$p2 = strrpos ($str, $e);
		return $p1 === false ? '' : $p2 == false ? substr ($str, $p1) : substr ($str, $p1 + 1, $p2 - $p1 - 1);
	}

	public static function fromFirst ($c, $str) {
		$p = strpos ($str, $c);
		return $p === false ? '' : substr ($str, $p + 1);
	}

	public static function untilFirst($c, $str) {
		$p = strpos ($str, $c);
		return $p === false ? $str : substr ($str, 0, $p);
	}

	public static function fromLast ($c, $str) {
		$p = strrpos ($str, $c);
		return $p === false ? '' : substr ($str, $p + 1);
	}

	public static function untilLast ($c, $str) {
		$p = strrpos ($str, $c);
		return $p === false ? $str : substr ($str, 0, $p);
	}
	
}
