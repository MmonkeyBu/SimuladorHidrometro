<#
  render_diagrams.ps1
  - Gera PNG/SVG do arquivo PlantUML usando plantuml.jar ou Docker.
#>
param(
  [string]$PlantUmlJarPath = "",
  [string]$OutputFormat = "png",
  [switch]$UseDocker = $false
)

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
$puml = Join-Path $scriptDir "diagrama_flowchart.puml"
if (-Not (Test-Path $puml)) { Write-Error "Arquivo $puml não encontrado."; exit 1 }

if ($UseDocker) {
  Write-Host "Renderizando usando Docker..."
  docker run --rm -v "${scriptDir}:/workspace" plantuml/plantuml -t$OutputFormat /workspace/diagrama_flowchart.puml
} else {
  if (-not $PlantUmlJarPath) { Write-Error "Defina -PlantUmlJarPath para o caminho do plantuml.jar ou use -UseDocker."; exit 1 }
  java -jar $PlantUmlJarPath -t$OutputFormat $puml
}

Write-Host "Renderização concluída. Procure os arquivos gerados no diretório do script."