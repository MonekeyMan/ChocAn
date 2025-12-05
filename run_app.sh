#!/bin/bash
# ChocAn Application Runner
# Edited by Wheeler Knight on 12/5/2025 - JSON data now stored in database/ folder

echo "Running application..."
echo "If you see a 'Permission denied' error, try running: chmod +x run_app.sh"

# Get the directory of this script (no hardcoding)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$SCRIPT_DIR"

cd "$PROJECT_ROOT" || exit 1

# Ensure database folder exists
if [ ! -d "database" ]; then
    echo "Creating database folder..."
    mkdir -p database
fi

# Launch application with DISPLAY for GUI
export DISPLAY=${DISPLAY:-:0}
java -cp "bin:lib/gson-2.10.1.jar" chocan.Main &

sleep 2
ps aux | grep "[c]hocan.Main"
